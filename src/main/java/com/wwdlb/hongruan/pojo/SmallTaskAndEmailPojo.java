package com.wwdlb.hongruan.pojo;

import com.wwdlb.hongruan.model.SmallTask;

public class SmallTaskAndEmailPojo {
    private String receiveTaskPersonalEmail;

    private Integer smalltaskid;

    private String smalltaskname;

    private String smalltaskdetail;

    private String endtime;

    private String havefinished;

    private String finishtime;

    public SmallTaskAndEmailPojo(SmallTask smallTask, String receiveTaskPersonalEmail) {
        this.receiveTaskPersonalEmail = receiveTaskPersonalEmail;
        this.smalltaskid = smallTask.getSmalltaskid();
        this.smalltaskname = smallTask.getSmalltaskname();
        this.smalltaskdetail = smallTask.getSmalltaskdetail();
        this.endtime = smallTask.getEndtime();
        this.havefinished = smallTask.getHavefinished();
        this.finishtime = smallTask.getFinishtime();
    }

    public String getReceiveTaskPersonalEmail() {
        return receiveTaskPersonalEmail;
    }

    public void setReceiveTaskPersonalEmail(String receiveTaskPersonalEmail) {
        this.receiveTaskPersonalEmail = receiveTaskPersonalEmail;
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
}
