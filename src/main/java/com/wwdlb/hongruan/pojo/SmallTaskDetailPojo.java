package com.wwdlb.hongruan.pojo;

import com.wwdlb.hongruan.model.SmallTaskAndNumberProgress;

import java.util.ArrayList;

public class SmallTaskDetailPojo {
    private Integer smalltaskid;

    private String smalltaskname;

    private String smalltaskdetail;

    private String endtime;

    private String havefinished;

    private String finishtime;

    private Integer safetyGrade;

    private Integer priority;

    private SmallTaskAndNumberProgress smallTaskAndNumberProgress;

    private ArrayList<CustomProgressPojo> customProgressPojos;

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

    public SmallTaskAndNumberProgress getSmallTaskAndNumberProgress() {
        return smallTaskAndNumberProgress;
    }

    public void setSmallTaskAndNumberProgress(SmallTaskAndNumberProgress smallTaskAndNumberProgress) {
        this.smallTaskAndNumberProgress = smallTaskAndNumberProgress;
    }

    public ArrayList<CustomProgressPojo> getCustomProgressPojos() {
        return customProgressPojos;
    }

    public void setCustomProgressPojos(ArrayList<CustomProgressPojo> customProgressPojos) {
        this.customProgressPojos = customProgressPojos;
    }
}
