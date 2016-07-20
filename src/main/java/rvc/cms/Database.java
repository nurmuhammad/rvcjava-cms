package rvc.cms;

import org.h2.jdbcx.JdbcConnectionPool;
import org.javalite.activejdbc.Base;
import rvc.cms.init.Config;

/**
 * Created by Nurmuhammad on 20.07.2016.
 */
public class Database {

    public static Database instance = new Database();

    JdbcConnectionPool pool;

    Database() {
        pool = JdbcConnectionPool.create(Config.get("database.jdbc.url"), Config.get("database.user"), Config.get("database.password"));
        pool.setMaxConnections(Config.get("ds.pool.max-size", 30));
    }

    public static JdbcConnectionPool pool(){
        return instance.pool;
    }

    public static void open(){
        Base.open(pool());
    }

    public static void close(){
        Base.close();
    }

}
