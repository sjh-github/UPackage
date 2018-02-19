package com.wwdlb.hongruan.model;

public class IPWhitelist {
    private Integer ipwhitelistid;

    private String startaddress;

    private String endaddress;

    private String remark;

    public Integer getIpwhitelistid() {
        return ipwhitelistid;
    }

    public void setIpwhitelistid(Integer ipwhitelistid) {
        this.ipwhitelistid = ipwhitelistid;
    }

    public String getStartaddress() {
        return startaddress;
    }

    public void setStartaddress(String startaddress) {
        this.startaddress = startaddress == null ? null : startaddress.trim();
    }

    public String getEndaddress() {
        return endaddress;
    }

    public void setEndaddress(String endaddress) {
        this.endaddress = endaddress == null ? null : endaddress.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}