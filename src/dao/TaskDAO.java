package dao;

import util.HHDIDBUtil;

import java.sql.SQLException;

/**
 * Created by Ren on 2018/10/29.
 */
public class TaskDAO {
    public static void main(String[] args)  {
        try {
            System.out.println(HHDIDBUtil.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

