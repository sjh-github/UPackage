package com.wwdlb.hongruan.pojo;

import com.wwdlb.hongruan.model.SmallTask;

public class SmallTaskDetailAndProgressPojo {
    private String receiveTaskEmail;

    private Integer taskid;

    private Integer smalltaskid;

    private String smalltaskname;

    private String smalltaskdetail;

    private String endtime;

    private String havefinished;

    private String finishtime;

    private Integer progress;

    private Integer safetyGrade;

    private Integer priority;

    public String getReceiveTaskEmail() {
        return receiveTaskEmail;
    }

    public void setReceiveTaskEmail(String receiveTaskEmail) {
        this.receiveTaskEmail = receiveTaskEmail;
    }

    public Integer getSmalltaskid() {
        return smalltaskid;
    }

    public void setSmalltaskid(Integer smalltaskid) {
        this.smalltaskid = smalltaskid;
    }

    public String getSmalltaskname() {
        return smalltaskname;
    }

    public void setSmalltaskname(String smalltaskname) {
        this.smalltaskname = smalltaskname;
    }

    public String getSmalltaskdetail() {
        return smalltaskdetail;
    }

    public void setSmalltaskdetail(String smalltaskdetail) {
        this.smalltaskdetail = smalltaskdetail;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getHavefinished() {
        return havefinished;
    }

    public void setHavefinished(String havefinished) {
        this.havefinished = havefinished;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getSafetyGrade() {
        return safetyGrade;
    }

    public void setSafetyGrade(Integer safetyGrade) {
        this.safetyGrade = safetyGrade;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public void setSmallTask(SmallTask smallTask) {
        if (smallTask != null) {
            this.smalltaskid = smallTask.getSmalltaskid();
            this.smalltaskname = smallTask.getSmalltaskname();
            this.smalltaskdetail = smallTask.getSmalltaskdetail();
            this.endtime = smallTask.getEndtime();
            this.havefinished = smallTask.getHavefinished();
            this.finishtime = smallTask.getFinishtime();
        }
    }
}
