package org;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

/**
 * Created by Nurmuhammad on 30.06.2016.
 */

public class H2 {
    public static void main(String[] args) throws Exception {

        /*HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:b:/tmp/h2test;MVCC=FALSE");
        config.setUsername("sa");
        config.setPassword("12345");*/
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

//        HikariDataSource ds = new HikariDataSource(config);


        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.setConnectionTestQuery("VALUES 1");
        config.addDataSourceProperty("URL", "jdbc:h2:b:/tmp/h2test");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("password", "12345");
        HikariDataSource ds = new HikariDataSource(config);

        long l = System.currentTimeMillis();

        Connection conn = ds.getConnection();
        Statement st = conn.createStatement();

        /*for (int i = 1; i < 1000; i++) {
            UUID uuid = UUID.randomUUID();
            st.execute("INSERT INTO USER (id, name) values (" + i + ", '" + uuid.toString() + "')");
        }*/

        ResultSet rs = st.executeQuery("select * from USER");
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("id="+id+"; name="+name);
        }

        rs.close();
        st.close();
        conn.close();

        System.out.println(System.currentTimeMillis()-l);

    }
}
