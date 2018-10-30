package dao;

import bean.Task;
import util.DateUtil;
import util.HHDIDBUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ren on 2018/10/29.
 */
public class TaskDAO {
    List<Task> tasks = new ArrayList<>();
    public static void main(String[] args)  {
        new TaskDAO().searchTask("t_jk_zlsh_cczm");
    }

    public void searchTask( String tableName) {
        List<Task> tasks_E = new ArrayList<>();
        tableName = "%" + tableName + "%";
        try {
            Connection c = HHDIDBUtil.getConnection();
            String searchTask_E = "SELECT a.TASK_ID,a.TASK_NO,a.TASK_NAME,a.SOURCE_TABLES,a.TARGET_TABLE,b.CONNECTION_NAME SOURCE_CONNECTION_NAME,\n" +
                    "c.CONNECTION_NAME TARGET_CONNECTION_Name,a.CREATED_TIME,a.MODIFIED_TIME,d.NAME MODIFIEDER FROM  HHDI.PUBLIC.TB_TASK_E a\n" +
                    "LEFT JOIN HHDI.PUBLIC.TB_DB_CONNECTION b ON a.SOURCE_CONNECTION=b.CONNECTION_ID\n" +
                    "LEFT JOIN HHDI.PUBLIC.TB_DB_CONNECTION c ON a.TARGET_CONNECTION=c.CONNECTION_ID\n" +
                    "LEFT JOIN HHDI.PUBLIC.TB_SYS_USER d ON a.MODIFIEDER=d.ID\n" +
                    "WHERE a.STATEFLAG='T' AND (a.SOURCE_TABLES LIKE ? OR a.TARGET_TABLE like ?) ";
            PreparedStatement ps = c.prepareStatement(searchTask_E);
            ps.setString(1, tableName);
            ps.setString(2, tableName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task task=new Task();
                task.setTaskId(rs.getString("TASK_ID"));
                task.setTaskName(rs.getString("TASK_NO"));
                task.setTaskName(rs.getString("TASK_NAME"));
                task.setSourceTables(rs.getString("SOURCE_TABLES"));
                task.setTargetTables(rs.getString("TARGET_TABLE"));
                task.setSourceConnectionName(rs.getString("SOURCE_CONNECTION_NAME"));
                task.setTargetConnectionName(rs.getString("TARGET_CONNECTION_Name"));
                task.setCreateTime(DateUtil.t2d(rs.getTimestamp("CREATED_TIME")) );
                task.setModifiedTime(DateUtil.t2d(rs.getTimestamp("MODIFIED_TIME")));
                task.setModifier(rs.getString("MODIFIEDER"));
                tasks_E.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

