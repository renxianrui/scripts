package bean;

/**
 * Created by Ren on 2018/10/28.
 */
public class Report {
    private int id;//id
    private int parentid;//parentid
    private String systemPath;//报表挂载目录
    private String serverPath;//报表服务器存放目录

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSystemPath() {
        return systemPath;
    }

    public void setSystemPath(String systemPath) {
        this.systemPath = systemPath;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }
    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }


}
