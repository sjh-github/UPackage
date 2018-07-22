package com.wwdlb.hongruan.model;

public class CustomProgress {
    private Integer customprogressid;

    private String remark;

    private String havefinished;

    private String finishtime;

    public Integer getCustomprogressid() {
        return customprogressid;
    }

    public void setCustomprogressid(Integer customprogressid) {
        this.customprogressid = customprogressid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public CustomProgress(String remark, String havefinished, String finishtime) {
        this.remark = remark;
        this.havefinished = havefinished;
        this.finishtime = finishtime;
    }

    public CustomProgress(Long customprogressid, String remark, String havefinished, String finishtime) {
        this.customprogressid = Integer.parseInt(customprogressid.toString());
        this.remark = remark;
        this.havefinished = havefinished;
        this.finishtime = finishtime;
    }

    public CustomProgress(Integer customprogressid, String remark, String havefinished, String finishtime) {
        this.customprogressid = customprogressid;
        this.remark = remark;
        this.havefinished = havefinished;
        this.finishtime = finishtime;
    }

    @Override
    public String toString() {
        return "CustomProgress{" +
                "customprogressid=" + customprogressid +
                ", remark='" + remark + '\'' +
                ", havefinished='" + havefinished + '\'' +
                ", finishtime='" + finishtime + '\'' +
                '}';
    }
}