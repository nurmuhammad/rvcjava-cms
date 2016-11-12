package rvc.cms;

import java.sql.*;
import java.util.*;

/**
 * Created by Nurmuhammad on 09.11.2016.
 */
public class China {
    String  ieroglif;
    String  pinyin;
    String  tarjimasi;
    String  misol;

    void trim(){
        tarjimasi = tarjimasi.replace(String.valueOf((char) 160), "").trim();
    }

    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:B:/dic/china.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM china;" );

            Map<String, China> map = new LinkedHashMap<>();
            Map<String, China> map2 = new TreeMap<>();
            List<China> list = new ArrayList<>();

            while ( rs.next() ) {
                China china = new China();
                china.ieroglif = rs.getString("ieroglif");
                china.pinyin = rs.getString("pinyin");
                china.tarjimasi = rs.getString("tarjimasi");
                china.misol = rs.getString("misol");
                china.trim();

                list.add(china);

                String soz[] = china.tarjimasi.split(",");

                for(String s : soz){
                    String s1 = s.replace(String.valueOf((char) 160), "").trim();
                    if($.isEmpty(s1)){
                        continue;
                    }
                    map.put(s1, china);
                    map2.put(s1, china);
                }
            }

            rs.close();
            stmt.close();

            System.out.println("Inserting ... ");

            String query = "INSERT INTO uzb (soz, ieroglif, pinyin, misol, sozlar) values(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = c.prepareStatement(query);
            for(String s : map2.keySet()){
                China china = map2.get(s);
                pstmt.setString(1, s);
                pstmt.setString(2, china.ieroglif);
                pstmt.setString(3, china.pinyin);
                pstmt.setString(4, china.misol);
                pstmt.setString(5, china.tarjimasi);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            c.commit();
            pstmt.close();

            System.out.println("Inserting ... ");

            query = "INSERT INTO china1 (ieroglif, pinyin, tarjimasi, misol) values(?, ?, ?, ?)";

            pstmt = c.prepareStatement(query);
            for(China china : list){
                pstmt.setString(1, china.ieroglif);
                pstmt.setString(2, china.pinyin);
                pstmt.setString(3, china.tarjimasi);
                pstmt.setString(4, china.misol);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            c.commit();

            pstmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
