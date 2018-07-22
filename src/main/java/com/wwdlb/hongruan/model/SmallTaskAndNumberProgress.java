package com.wwdlb.hongruan.model;

public class SmallTaskAndNumberProgress {
    private Integer id;

    private Integer smalltaskid;

    private Integer numberprogress;

    private Integer finishednumber;

    private String updatetime;

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

    public Integer getNumberprogress() {
        return numberprogress;
    }

    public void setNumberprogress(Integer numberprogress) {
        this.numberprogress = numberprogress;
    }

    public Integer getFinishednumber() {
        return finishednumber;
    }

    public void setFinishednumber(Integer finishednumber) {
        this.finishednumber = finishednumber;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public SmallTaskAndNumberProgress(Integer smalltaskid, Integer numberprogress, Integer finishednumber, String updatetime) {
        this.smalltaskid = smalltaskid;
        this.numberprogress = numberprogress;
        this.finishednumber = finishednumber;
        this.updatetime = updatetime;
    }

    public SmallTaskAndNumberProgress() {
    }
}