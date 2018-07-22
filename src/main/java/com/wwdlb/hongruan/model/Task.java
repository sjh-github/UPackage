package com.wwdlb.hongruan.model;

public class Task {
    private Integer taskid;

    private String taskname;

    private String taskdetail;

    private Integer safetygrade;

    private Integer priority;

    private String starttime;

    private String endtime;

    private Double progress;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getTaskdetail() {
        return taskdetail;
    }

    public void setTaskdetail(String taskdetail) {
        this.taskdetail = taskdetail == null ? null : taskdetail.trim();
    }

    public Integer getSafetygrade() {
        return safetygrade;
    }

    public void setSafetygrade(Integer safetygrade) {
        this.safetygrade = safetygrade;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Task(String taskname, String taskdetail, Integer safetygrade, Integer priority, String starttime, String endtime) {
        this.taskid = taskid;
        this.taskname = taskname;
        this.taskdetail = taskdetail;
        this.safetygrade = safetygrade;
        this.priority = priority;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public Task() {
    }
}