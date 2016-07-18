package rvc.cms.dao;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import rvc.cms.init.PoolService;
import rvc.cms.init.Sql2oFactory;
import rvc.cms.model.aModel;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @author nurmuhammad
 */

public abstract class Dao<E> {

    private static final Logger logger = LoggerFactory.getLogger(Dao.class);

    public static final ObjectPool<Sql2o> pool = new GenericObjectPool<>(new Sql2oFactory());

    public Class<E> className;

    @SuppressWarnings("unchecked")
    public Dao() {
        try {
            className = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Throwable e) {
            logger.error("Throws error when detecting generic class.", e);
            throw new RuntimeException("Please see the logs.");
        }
    }

    public Query addColumnMapping(Query query) {
        Map<String, String> map = aModel.getColumnMaps().get(className);
        if (map == null || map.isEmpty()) return query;
        map.keySet().stream().forEach(s -> query.addColumnMapping(s, map.get(s)));

        return query;
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
