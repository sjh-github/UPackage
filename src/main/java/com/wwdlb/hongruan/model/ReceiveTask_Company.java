package com.wwdlb.hongruan.model;

public class ReceiveTask_Company {
    private String companyemail;

    private String companypassword;

    private String companyname;

    private String companysummary;

    private String havechecked;

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail == null ? null : companyemail.trim();
    }

    public String getCompanypassword() {
        return companypassword;
    }

    public void setCompanypassword(String companypassword) {
        this.companypassword = companypassword == null ? null : companypassword.trim();
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