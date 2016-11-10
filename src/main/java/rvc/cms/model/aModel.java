package rvc.cms.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rvc.cms.$;
import rvc.cms.HibernateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

/**
 * @author nurmuhammad
 */

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class aModel implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(aModel.class);

    @Transient
    private transient Map<String, aModel> cacheAttributes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "created")
    public Long created;

    @Column(name = "changed")
    public Long changed;

    @Column(name = "status_comment", length = 255)
    public String comment;

    @Column(name = "status_username", length = 64)
    public String username;

    @Column(name = "status_id", length = 16)
    public String statusId;

    @Column(name = "schema", length = 255)
    public String schema;

    public <T> T get(String field) {
        try {
            java.lang.reflect.Field fld = getClass().getField(field);
            return (T) fld.get(this);
        } catch (NoSuchFieldException e) {
            try {
                return (T)$.invoke(this, field);
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
            if (this.created == null) {
                this.created = Instant.now().getEpochSecond();
            }
            this.changed = Instant.now().getEpochSecond();

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

    public static long count(Class<? extends aModel> aClass, String schema) {
        return tx((session) -> session.createQuery("select count(*) from " + aClass.getSimpleName()).uniqueResult(), schema);
    }

    public static long count(Class<? extends aModel> aClass) {
        return count(aClass, HibernateUtil.DEFAULT_PROPERTIES);
    }

    public static long count(Class<? extends aModel> aClass, String schema, String where, Object... params) {
        return tx((session) -> {
            org.hibernate.query.Query query = session.createQuery("select count(*) from " + aClass.getSimpleName() + " where " + where);
            for (int i = 1; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            return query.uniqueResult();
        }, schema);
    }

    public static long count(Class<? extends aModel> aClass, String where, Object... params){
        return count(aClass, HibernateUtil.DEFAULT_PROPERTIES, where, params);
    }

    public static <T extends aModel> List<T> where(Class<T> aClass, String schema, String where, Object... params){
        return tx(
                (session) -> {
                    org.hibernate.query.Query query = session.createQuery("from " + aClass.getSimpleName() + " where " + where);
                    for (int i = 1; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                    return query.list();
                }, schema);
    }

    public static <T extends aModel> List<T> where(Class<T> aClass, String where, Object... params){
        return where(aClass, HibernateUtil.DEFAULT_PROPERTIES, where, params);
    }

    public static org.hibernate.query.Query query(String query, String schema, Object... params) {
        org.hibernate.query.Query q = HibernateUtil.getSession(schema).createQuery(query);
        for (int i = 1; i < params.length; i++) {
            q.setParameter(i, params[i]);
        }
        return q;
    }

    public static org.hibernate.query.Query query(String query, Object... params){
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
