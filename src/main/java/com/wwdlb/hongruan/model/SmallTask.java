package com.wwdlb.hongruan.model;

public class SmallTask {
    private Integer smalltaskid;

    private String smalltaskname;

    private String smalltaskdetail;

    private String havefinished;

    private String finishtime;

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
        this.smalltaskname = smalltaskname == null ? null : smalltaskname.trim();
    }

    public String getSmalltaskdetail() {
        return smalltaskdetail;
    }

    public void setSmalltaskdetail(String smalltaskdetail) {
        this.smalltaskdetail = smalltaskdetail == null ? null : smalltaskdetail.trim();
    }

    public String getHavefinished() {
        return havefinished;
    }

    public void setHavefinished(String havefinished) {
        this.havefinished = havefinished == null ? null : havefinished.trim();
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime == null ? null : finishtime.trim();
    }

    public SmallTask() {
    }

    public SmallTask(String smalltaskname, String smalltaskdetail, String havefinished, String finishtime) {
        this.smalltaskname = smalltaskname;
        this.smalltaskdetail = smalltaskdetail;
        this.havefinished = havefinished;
        this.finishtime = finishtime;
    }
}