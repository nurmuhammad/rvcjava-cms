package rvc.cms;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rvc.cms.init.HibernateInterceptor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nurmuhammad
 */

public class HibernateUtil {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    public final static String DEFAULT_PROPERTIES = "hibernate.properties";

    private static Map<String, SessionFactory> sessionFactoryMap = new HashMap<>();

    public static SessionFactory getSessionFactory() {
        return getSessionFactory(DEFAULT_PROPERTIES);
    }

    public static SessionFactory getSessionFactory(String key) {
        SessionFactory sessionFactory = sessionFactoryMap.get(key);
        if (sessionFactory != null) {
            return sessionFactory;
        }

        String properties = key.endsWith(".properties") ? key : key + ".properties";

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .loadProperties(properties);
        Map settings = builder.getSettings();

        final StandardServiceRegistry registry = builder.build();

        Configuration config = new Configuration();
        String s = (String) settings.get("hibernate.annotated.classes");
        Arrays.stream(s.split(",")).forEach(className -> {
            try {
                ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                config.addAnnotatedClass(classLoader.loadClass(className.trim()));
            } catch (ClassNotFoundException e) {
                logger.error("Class not found:" + className, e);
            }
        });
//        config.setEntityNotFoundDelegate(); // TODO implement
        HibernateInterceptor interceptor = new HibernateInterceptor();
        config.setInterceptor(interceptor);
        sessionFactory = config.buildSessionFactory(registry);

        sessionFactoryMap.put(key, sessionFactory);

        return sessionFactory;
    }

    public static Session getSession() {
        return getSession(DEFAULT_PROPERTIES);
    }

    public static Session getSession(String key) {
        SessionFactory sessionFactory = getSessionFactory(key);
//        sessionFactory.getStatistics() //TODO enable-disable statistics by admin
        Session session = null;
        try {
//            logger.info("Trying to get current session object.");
            if (sessionFactory.getCurrentSession() != null) {
                session = sessionFactory.getCurrentSession();
            }
        } catch (Exception e) {
            logger.warn("no current session context. " , e);
            try {
                logger.info("Trying to open session object.");
                session = sessionFactory.openSession();
            } catch (HibernateException e1) {
                logger.error("Error when opening session." , e);
                e1.printStackTrace();
            }
        }
        assert session != null;
        return session;
    }


}
