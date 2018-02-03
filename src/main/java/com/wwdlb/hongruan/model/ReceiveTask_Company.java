package com.wwdlb.hongruan.model;

public class ReceiveTask_Company {
    private Integer companyid;

    private String companyname;

    private String companysummary;

    private String havechecked;

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getCompanysummary() {
        return companysummary;
    }

    public void setCompanysummary(String companysummary) {
        this.companysummary = companysummary == null ? null : companysummary.trim();
    }

    public String getHavechecked() {
        return havechecked;
    }

    public void setHavechecked(String havechecked) {
        this.havechecked = havechecked == null ? null : havechecked.trim();
    }
}