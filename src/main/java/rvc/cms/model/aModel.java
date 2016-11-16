package rvc.cms.model;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rvc.cms.$;
import rvc.cms.HibernateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nurmuhammad
 */

@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public abstract class aModel implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(aModel.class);

    Long id;
    Integer created;
    Integer changed;
    String schema;

    @Transient
    private transient Map<String, aModel> cacheAttributes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "created")
    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    @Column(name = "changed")
    public Integer getChanged() {
        return changed;
    }

    public void setChanged(Integer changed) {
        this.changed = changed;
    }

    @Column(name = "_schema", length = 255)
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @PrePersist
    @PreUpdate
    void pre() {
        if (this.created == null) {
            this.created = (int) Instant.now().getEpochSecond();
        }
        this.changed = (int) Instant.now().getEpochSecond();

        //TODO : store user data
        //TODO : impl get schema if null
    }

    @Transient
    public <T> T get(String field) {
        try {
            java.lang.reflect.Field fld = getClass().getField(field);
            return (T) fld.get(this);
        } catch (NoSuchFieldException e) {
            try {
                return (T) $.invoke(this, field);
            } catch (NoSuchMethodException ignored) {
                e.printStackTrace();
                return (T) attribute(field);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(String field, Object value) {
        try {
            java.lang.reflect.Field fld = getClass().getField(field);
            fld.set(this, value);
        } catch (NoSuchFieldException e) {
            if (value instanceof aModel) {
                attribute(field, (aModel) value);
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected <T extends aModel> T attribute(String attribute) {
        if (cacheAttributes == null) {
            cacheAttributes = new HashMap<>();
        }
        return (T) cacheAttributes.get(attribute);
    }

    protected <T extends aModel> void attribute(String attribute, T model) {
        if (cacheAttributes == null) {
            cacheAttributes = new HashMap<>();
        }
        cacheAttributes.put(attribute, model);
    }

    public void saveOrUpdate(String schema) {
        this.schema = schema;
        tx((session -> {
            session.saveOrUpdate(this);
            return this;
        }), schema);
    }

    public void saveOrUpdate() {
        schema = schema == null ? HibernateUtil.DEFAULT_PROPERTIES : schema;
        saveOrUpdate(schema);
    }

    public void delete(String schema) {
        this.schema = schema;
        tx(session -> {
            session.delete(this);
            this.cacheAttributes.clear();
            return this;
        }, schema);
    }

    public void delete() {
        schema = schema == null ? HibernateUtil.DEFAULT_PROPERTIES : schema;
        delete(schema);
    }

    public static <T> T lazy(T lazy, String schema) {
        if (lazy instanceof HibernateProxy) {
            LazyInitializer lazyInitializer = ((HibernateProxy) lazy).getHibernateLazyInitializer();
            if (lazyInitializer.getSession() == null) {
                SharedSessionContractImplementor session =
                        (SharedSessionContractImplementor) HibernateUtil.getSession(schema);
                Transaction tx = session.getTransaction();
                if (tx.getStatus() != TransactionStatus.ACTIVE) {
                    tx.begin();
                }
                lazyInitializer.setSession(session);
                Hibernate.initialize(lazy);
                lazyInitializer.initialize();
                tx.commit();
            }
        }
        return lazy;
    }

    public static <T extends aModel> T findById(Class<T> aClass, Long id, String schema) {
        return tx((session) -> session.get(aClass, id), schema);
    }

    public static <T extends aModel> T findById(Class<T> aClass, Long id) {
        return findById(aClass, id, HibernateUtil.DEFAULT_PROPERTIES);
    }

    public static <T extends aModel> List<T> findAll(Class<T> aClass, String schema) {
        return tx((session) -> session.createQuery("from " + aClass.getSimpleName()).list(), schema);
    }

    public static <T extends aModel> List<T> findAll(Class<T> aClass) {
        return findAll(aClass, HibernateUtil.DEFAULT_PROPERTIES);
    }

    public static void deleteById(Class<? extends aModel> aClass, Object id, String schema) {

    }

    public static long count(Class<? extends aModel> aClass, String schema) {
        return tx((session) -> session.createQuery("select count(*) from " + aClass.getSimpleName()).uniqueResult(), schema);
    }

    public static long count(Class<? extends aModel> aClass) {
        return count(aClass, HibernateUtil.DEFAULT_PROPERTIES);
    }

    public static long count(Class<? extends aModel> aClass, String schema, String where, Object... params) {
        return tx((session) -> {
            org.hibernate.query.Query query = session.createQuery("select count(*) from " + aClass.getSimpleName() + " where " + where);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            return query.uniqueResult();
        }, schema);
    }

    public static long count(Class<? extends aModel> aClass, String where, Object... params) {
        return count(aClass, HibernateUtil.DEFAULT_PROPERTIES, where, params);
    }

    public static <T extends aModel> List<T> where(Class<T> aClass, String schema, String where, Object... params) {
        return tx(
                (session) -> {
                    org.hibernate.query.Query query = session.createQuery("from " + aClass.getSimpleName() + " where " + where);
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                    return query.list();
                }, schema);
    }

    public static <T extends aModel> List<T> where(Class<T> aClass, String where, Object... params) {
        return where(aClass, HibernateUtil.DEFAULT_PROPERTIES, where, params);
    }

    public static org.hibernate.query.Query query(String query, String schema, Object... params) {
        org.hibernate.query.Query q = HibernateUtil.getSession(schema).createQuery(query);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i, params[i]);
        }
        return q;
    }

    public static org.hibernate.query.Query query(String query, Object... params) {
        return query(query, HibernateUtil.DEFAULT_PROPERTIES, params);
    }

    public static <T> T tx(Result r) {
        return tx(r, HibernateUtil.DEFAULT_PROPERTIES);
    }

    public static <T> T tx(Result r, String schema) {
        Session session = HibernateUtil.getSession(schema);
        Transaction tx = session.getTransaction();
        T result = null;
        try {
            if (tx.getStatus() != TransactionStatus.ACTIVE) {
                tx.begin();
            }
            result = (T) r.execute(session);
            tx.commit();
        } catch (Exception e) {
            logger.error("Error when calling saveOrUpdate method. ", e);
            tx.rollback();
        }
        return result;
    }

    public interface Result {
        Object execute(Session session);
    }
}
