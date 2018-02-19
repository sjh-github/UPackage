package com.wwdlb.hongruan.model;

public class SmallTaskAndCustomProgress {
    private Integer id;

    private Integer smalltaskid;

    private Integer customprogressid;

    private Integer progressorder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSmalltaskid() {
        return smalltaskid;
    }

    public void setSmalltaskid(Integer smalltaskid) {
        this.smalltaskid = smalltaskid;
    }

    public Integer getCustomprogressid() {
        return customprogressid;
    }

    public void setCustomprogressid(Integer customprogressid) {
        this.customprogressid = customprogressid;
    }

    public Integer getProgressorder() {
        return progressorder;
    }

    public void setProgressorder(Integer progressorder) {
        this.progressorder = progressorder;
    }

    public SmallTaskAndCustomProgress(Integer smalltaskid, Integer customprogressid, Integer progressorder) {
        this.smalltaskid = smalltaskid;
        this.customprogressid = customprogressid;
        this.progressorder = progressorder;
    }
}