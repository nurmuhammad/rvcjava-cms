package rvc.cms.dao;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import rvc.cms.init.PoolService;
import rvc.cms.init.Sql2oFactory;
import rvc.cms.model.aModel;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author nurmuhammad
 */

public abstract class Dao<E> {

    private static final Logger logger = LoggerFactory.getLogger(Dao.class);

    public static final ObjectPool<Sql2o> pool = new GenericObjectPool<>(new Sql2oFactory());

    public Class<E> className;
    private String table;

    @SuppressWarnings("unchecked")
    public Dao() {
        try {
            className = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Throwable e) {
            logger.error("Throws error when detecting generic class.", e);
            throw new RuntimeException("Please see the logs.");
        }

        init();
    }

    public Dao(Class<E> className) {
        this.className = className;
        init();
    }

    private void init() {
        table = aModel.table(className);
    }

    public E findById(long id) {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            E object = addColumnMapping(con.createQuery("select * from " + table + " where id=:id"))
                    .addParameter("id", id)
                    .executeAndFetchFirst(className);
            return object;
        } finally {
            returnSql2o(sql2o);
        }
    }

    public E insert(E object) {
        StringBuilder fields = new StringBuilder();
        fields.append("insert into ").append(table).append("(");
        StringBuilder values = new StringBuilder();
        Field[] fs = object.getClass().getDeclaredFields();
        for (int i = 0; i > fs.length; i++) {
            Field field = fs[i];
            fields.append(field.getName());
            values.append(":").append(field.getName());
            if (i < fs.length - 1) {
                fields.append(",");
                values.append(",");
            }
        }
        fields.append(") values (").append(values).append(")");
        return insert(object, fields.toString());
    }

    public E insert(E object, String sql) {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            addColumnMapping(con.createQuery(sql)).bind(object).executeUpdate();
            try {
                Field field = object.getClass().getDeclaredField("id");
                Long id = con.getKey(Long.class);
                field.set(object, id);
            } catch (Exception e) {
                logger.error("Throws error when inserting object to database.", e);
                throw new RuntimeException("Throws error when inserting object to database.");
            }
            return object;
        } finally {
            returnSql2o(sql2o);
        }
    }

    public E update(E object) {
        return object;
    }

    public E update(E object, String sql) {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            addColumnMapping(con.createQuery(sql)).bind(object).executeUpdate();
            return object;
        } finally {
            returnSql2o(sql2o);
        }
    }

    public E delete(E object) {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            Field field = object.getClass().getDeclaredField("id");
            Long id = (Long) field.get(object);
            con.createQuery("delete from " + table + " where id=:id")
                    .addParameter("id", id)
                    .executeUpdate();
            return object;
        } catch (Exception e) {
            logger.error("Throws error when deleting object from database.", e);
            throw new RuntimeException("Throws error when deleting object from database.");
        } finally {
            returnSql2o(sql2o);
        }
    }

    public long count() {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            return con.createQuery("select count(*) from " + table)
                    .executeScalar(Long.class);
        } finally {
            returnSql2o(sql2o);
        }
    }

    public List<E> list() {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            return addColumnMapping(con.createQuery("select * from " + table))
                    .executeAndFetch(className);
        } finally {
            returnSql2o(sql2o);
        }
    }

    public List<E> list(long limit, long offset) {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            return addColumnMapping(con.createQuery("select * from " + table + " limit :limit offset :offset"))
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(className);
        } finally {
            returnSql2o(sql2o);
        }
    }

    public Query addColumnMapping(Query query) {
        Map<String, String> map = aModel.getColumnMaps().get(className);
        if (map == null || map.isEmpty()) return query;
        map.keySet().stream().forEach(s -> query.addColumnMapping(s, map.get(s)));

        return query;
    }

    public static List<Map<String, Object>> query(String sql) {
        Sql2o sql2o = sql2o();
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetchTable().asList();
        } finally {
            returnSql2o(sql2o);
        }
    }

    public static synchronized Sql2o sql2o() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            logger.error("Throws error when borrowing Sql2o object from the pool.", e);
        }
        logger.info("Trying return new Sql2o object outside from the pool.");
        return new Sql2o(PoolService.getDataSource());
    }

    public static synchronized void returnSql2o(Sql2o sql2o) {
        try {
            sql2o.getDefaultColumnMappings().clear();
            pool.returnObject(sql2o);
        } catch (Exception e) {
            logger.error("Throws error when returning Sql2o object to the pool.", e);
        }
    }

}
