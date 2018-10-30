package util;

import com.fr.third.JAI.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by Ren on 2018/10/29.
 */
public class PropertiesUtil {
   public static String jdbc;
    public static String loginName;
    public static String password;
    public static String finedbPath;
    public static String reportletPath;
    static {
        Properties localProperties = new Properties();
        String strProperties = PropertiesUtil.class.getResource("/").getPath();
        strProperties = strProperties.substring(1, strProperties.indexOf("classes"));
        try {
            localProperties.load(new FileInputStream( strProperties+"db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

         String enable = localProperties.getProperty("enable");
        if(enable.equals("1")) {
            jdbc = localProperties.getProperty("hhdiJdbc");
            loginName = localProperties.getProperty("hhdiLoginName");
            password = localProperties.getProperty("hhdiPassword");
            finedbPath = localProperties.getProperty("finedbPath");
            reportletPath = localProperties.getProperty("reportletPath");
        }
    }


}
