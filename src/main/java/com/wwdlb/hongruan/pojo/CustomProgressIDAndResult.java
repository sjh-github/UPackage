package com.wwdlb.hongruan.pojo;

public class CustomProgressIDAndResult {
    private Integer customProgressID;
    private boolean haveFinihed;

    public CustomProgressIDAndResult(Integer customProgressID, boolean haveFinihed) {
        this.customProgressID = customProgressID;
        this.haveFinihed = haveFinihed;
    }

    public CustomProgressIDAndResult() {
    }

    public Integer getCustomProgressID() {
        return customProgressID;
    }

    public void setCustomProgressID(Integer customProgressID) {
        this.customProgressID = customProgressID;
    }

    public boolean isHaveFinihed() {
        return haveFinihed;
    }

    public void setHaveFinihed(boolean haveFinihed) {
        this.haveFinihed = haveFinihed;
    }
}
