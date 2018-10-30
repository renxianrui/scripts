package dao;

import bean.Report;
import util.PropertiesUtil;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ren on 2018/10/28.
 */
public class ReportDAO {
    private List<String> serverPaths = new ArrayList<>();

    //根据serverPaths中的模板路径查找所有的挂载目录，返回到List<report>中
    public List<Report> listReport() {
        List<Report> reports = new ArrayList<>();
        for (String s : serverPaths) {
            List<Report> searchReports = searchReport(s);
            reports.addAll(searchReports);
        }
        return reports;
    }

//    public static void main(String[] args) {
//        ReportDAO r = new ReportDAO();
//        File javaFile = new File("D:/LOLFolder/reportlets");
//
//        r.search(javaFile, "商场综合指标分析");
//        List<Report> reports = r.listReport();
//        for (Report report : reports) {
//            System.out.println(report.getSystemPath());
//        }
//    }
    public void listLostReport() {

    }

    //根据模板路径查找在决策系统中的挂载目录
    public List<Report> searchReport(String serverPath) {

        String finedbPath = PropertiesUtil.finedbPath;
        int parentid = -2;
        List<Report> reports = new ArrayList<>();

        try {
            Class.forName("com.fr.third.org.hsqldb.jdbcDriver");
            Connection c = DriverManager.getConnection("emb:jdbc:hsqldb:file:" + finedbPath + "/db", "sa", "");
//                Connection c = FinedbUtil.getConnection();
            String sql = " SELECT id,parent,name,reportletpath FROM FR_REPORTLETENTRY WHERE reportletpath=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, serverPath);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Report r = new Report();

                r.setId(rs.getInt(1));
                r.setParentid(rs.getInt(2));
                r.setSystemPath(rs.getString(3) + "\\");
                r.setServerPath(rs.getString(4));
                reports.add(r);
            }
            for (Report r : reports) {
                parentid = r.getParentid();
                while (parentid != -1 && parentid != -2) {
                    String reverseSql = "SELECT Id,parent,name FROM FR_FOLDERENTRY WHERE id=?";
                    PreparedStatement dirCReverse = c.prepareStatement(reverseSql);
                    dirCReverse.setInt(1, parentid);
                    ResultSet dirReverse = dirCReverse.executeQuery();
                    while (dirReverse.next()) {
                        parentid = dirReverse.getInt(2);
                        r.setSystemPath(dirReverse.getString(3) + "\\" + r.getSystemPath());
                        ;

                    }
                    dirCReverse.close();
                }
            }
            rs.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return reports;
    }

    //查找包含指定文本内容的模板文件，将查找到的模板目录加入到serverPaths中
    public void search(File file, String search) {
        String serverPath;
        if (file.isFile()) {
            if (search.trim().length() == 0) {//如果搜索内容为空，那么就只读取报表路径，不查找报表内容
                serverPath = file.getAbsolutePath();
                serverPath = serverPath.substring(serverPath.indexOf("reportlets") + "reportlets\\".length()).replaceAll("\\\\", "/");
                serverPaths.add(serverPath);
            } else {//如果搜索内容为空，那么找报表内容（包括报表名称）中包含搜索内容的报表目录
                if (file.getName().toLowerCase().endsWith(".frm") || file.getName().toLowerCase().endsWith(".cpt")) {
                    String fileContent = readFileContent(file);
                    if (fileContent.contains(search) || file.getName().toLowerCase().contains(search)) {
                        // System.out.printf("找到目标字符串:%s,在文件:%s%n",search,file);
                        serverPath = file.getAbsolutePath();
                        serverPath = serverPath.substring(serverPath.indexOf("reportlets") + "reportlets\\".length()).replaceAll("\\\\", "/");
                        //System.out.println(serverPath);
                        serverPaths.add(serverPath);
                    }
                }

            }
        }
        if (file.isDirectory()) {
            File[] fs = file.listFiles();
            for (File f : fs) {
                search(f, search);
            }
        }
    }

    //读取文件内容
    public static String readFileContent(File file) {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"))) {
            char[] all = new char[(int) file.length()];
            isr.read(all);
            return new String(all);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
