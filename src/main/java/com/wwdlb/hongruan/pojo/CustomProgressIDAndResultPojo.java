package com.wwdlb.hongruan.pojo;

public class CustomProgressIDAndResultPojo {
    private Integer customProgressID;
    private boolean haveFinished;

    public CustomProgressIDAndResultPojo(Integer customProgressID, boolean haveFinihed) {
        this.customProgressID = customProgressID;
        this.haveFinished = haveFinihed;
    }

    public CustomProgressIDAndResultPojo() {
    }

    public Integer getCustomProgressID() {
        return customProgressID;
    }

    public void setCustomProgressID(Integer customProgressID) {
        this.customProgressID = customProgressID;
    }

    public boolean isHaveFinihed() {
        return haveFinished;
    }

    public void setHaveFinihed(boolean haveFinihed) {
        this.haveFinished = haveFinihed;
    }
}
