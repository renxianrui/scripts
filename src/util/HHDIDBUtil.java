package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ren on 2018/10/29.
 */
public class HHDIDBUtil {
    static String jdbc= util.PropertiesUtil.jdbc; //= "jdbc:h2:tcp://127.0.0.1:9092/HHDI";
    static String loginName= util.PropertiesUtil.loginName; //= "haohe";
    static String password= util.PropertiesUtil.password;//= "123456";


    public static Connection getConnection() throws SQLException {
        System.out.println(jdbc+loginName+password);
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbc, loginName, password);
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }

}
