package rvc.cms;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Backup;
import org.h2.tools.Recover;
import org.h2.tools.Script;
import rvc.cms.init.Config;

import java.sql.SQLException;

/**
 * @author nurmuhammad
 */

public class Database2 {

    public static Database2 instance = new Database2();

    JdbcConnectionPool pool;

    Database2() {
        pool = JdbcConnectionPool.create(Config.get("database.jdbc.url"), Config.get("database.user"), Config.get("database.password"));
        pool.setMaxConnections(Config.get("ds.pool.max-size", 30));
    }

    public static JdbcConnectionPool pool() {
        return instance.pool;
    }

//    public static void open() {
//        Base.open(pool());
//    }

//    public static void close() {
//        Base.close();
//    }

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
