package rvc.cms;

import org.h2.tools.Server;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.h2.tools.Backup;
import org.h2.tools.Recover;
import org.h2.tools.Script;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import rvc.cms.init.Config;
import rvc.cms.model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author nurmuhammad
 */

public class Database {

    public static Database instance = new Database();

    public static void main(String[] args) throws Exception {
        Server webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8083").start();
        Database database = new Database();
        database.test2();
//        recovery();
//        backupZip();
//        backupSql();
//        database.setUp();
//        System.out.println("\n\n\n\n\n");
//        System.out.println("test");
//        System.out.println("\n\n\n\n\n");
    }

    void test1() {
        Vote vote = new Vote();
        vote.type = "type";
        vote.value = 3.4d;
        vote.saveOrUpdate();

        vote = new Vote();
        vote.type = "salom";
        vote.value = 500d;
        vote.saveOrUpdate();
        System.out.println("\n\n\n\n");
        long l = System.currentTimeMillis();
        System.out.println(Vote.findById(Vote.class, 130L).type);
        System.out.println(System.currentTimeMillis() - l);
        System.out.println("\n\n");
        l = System.currentTimeMillis();
        System.out.println(Vote.findAll(Vote.class));
        System.out.println(System.currentTimeMillis() - l);

        System.out.println("\n\n");
        l = System.currentTimeMillis();
        System.out.println(Vote.findAll(Vote.class));
        System.out.println(System.currentTimeMillis() - l);


        System.out.println("\n\n");
        l = System.currentTimeMillis();
        System.out.println("count=" + Vote.count(Vote.class));
        System.out.println(System.currentTimeMillis() - l);
    }

    void test2() {

        /*Vote vote = Vote.findById(Vote.class, 1657L);
        if ( vote.node instanceof HibernateProxy) {
            LazyInitializer lazyInitializer = ( (HibernateProxy) vote.node ).getHibernateLazyInitializer();
            if(lazyInitializer.getSession()==null){
                SharedSessionContractImplementor session = (SharedSessionContractImplementor)HibernateUtil.getSession();
                Transaction tx = session.getTransaction();
                if (tx.getStatus() != TransactionStatus.ACTIVE) {
                    tx.begin();
                }
                lazyInitializer.setSession(session);
                Hibernate.initialize(vote.node);
                lazyInitializer.initialize();
                vote.node = (Node)lazyInitializer.getImplementation();
                tx.commit();
            }
            System.out.println();
        }

        System.out.println(vote.node);
        System.out.println(vote.node.id);
        System.out.println(vote.node.changed);
        */

        HibernateUtil.getSession().beginTransaction();
        List list = FieldType.query("select f from FieldType f where f.id < ?", 10L).list();
        HibernateUtil.getSession().getTransaction().commit();

//        FieldType fieldType = FieldType.findById(FieldType.class, 1L);
//        System.out.println(fieldType);

        if(1==1) return;

        long i = 0;

        Random random = new Random();

        while (true) {
            FieldType objects = new FieldType();
            objects.name = $.b64encode(UUID.randomUUID().toString()).substring(0, 10).toUpperCase();
            objects.type = "template";
            objects.setting(objects.name, UUID.randomUUID().toString().toUpperCase());
            objects.setting(objects.type, UUID.randomUUID().toString().toUpperCase());
            objects.setting("h2", null);
            objects.required = true;
            objects.label = "localhost";

            /*long id = random.nextInt(1000)+1;
            Node node = Node.findById(Node.class, id);

            objects.node = node;*/

            objects.saveOrUpdate();

            i++;

            if( i % 100 == 0 ) {
                System.out.println(i);
            }

            if (i > 1000) {
                break;
            }
        }

    }

    protected void setUp() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Vote vote = new Vote();
            vote.type = "test";

            session.saveOrUpdate(vote);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static void backupSql() throws SQLException {
        String dburl = Config.get("database.jdbc.url");
        String[] bkp = {"-url", dburl, "-user", Config.get("database.user"), "-password", Config.get("database.password")};
        Script.main(bkp);
    }

    public static void backupZip() throws SQLException {
        String dburl = Config.get("database.jdbc.url");
        String[] bkp = {"-url", dburl, "-user", Config.get("database.user"), "-password", Config.get("database.password")};
        Script.main(bkp);

        Backup.execute("backup.zip", "b:\\tmp\\rvc\\", "h2cms", false);
    }

    public static void recovery() throws SQLException {
        Recover.execute("b:\\tmp\\rvc\\", "h2cms");
    }
}
