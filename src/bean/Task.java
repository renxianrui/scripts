package bean;

import java.util.Date;

/**
 * Created by Ren on 2018/10/29.
 */
public class Task {
    private String taskNO;//任务编号
    private String taskName;//任务名称
    private String sourceTables;//源表
    private String transScript;//目标表
    private Date createTime;//创建时间
    private Date modifiedTime;//修改时间
    private String modifier;//修改人
    private String jobName;//作业名称
    private String schedule;//调度名称

    public String getTaskNO() {
        return taskNO;
    }

    public void setTaskNO(String taskNO) {
        this.taskNO = taskNO;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSourceTables() {
        return sourceTables;
    }

    public void setSourceTables(String sourceTables) {
        this.sourceTables = sourceTables;
    }

    public String getTransScript() {
        return transScript;
    }

    public void setTransScript(String transScript) {
        this.transScript = transScript;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }


}
