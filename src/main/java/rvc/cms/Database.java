package rvc.cms;

import org.h2.tools.Server;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.h2.tools.Backup;
import org.h2.tools.Recover;
import org.h2.tools.Script;
import rvc.cms.init.Config;
import rvc.cms.model.*;

import java.sql.SQLException;
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
        long i = 0;
        while (true) {
            Objects objects = new Objects();
            objects.name = $.b64encode(UUID.randomUUID().toString()).substring(0, 10).toUpperCase();
            objects.description = $.b64encode(UUID.randomUUID().toString());
            objects.type = "template";
            objects.setting(objects.name, objects.description);
            objects.setting(objects.type, objects.description);
            objects.setting("sum", objects.type);

            objects.saveOrUpdate();

            i++;

            if (i > 10000) {
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
