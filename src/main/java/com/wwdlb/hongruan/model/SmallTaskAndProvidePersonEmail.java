package com.wwdlb.hongruan.model;

public class SmallTaskAndProvidePersonEmail {
    private Integer smalltaskandprovideemailid;

    private String email;

    private Integer smalltaskid;

    public SmallTaskAndProvidePersonEmail(Integer smalltaskandprovideemailid, String email, Integer smalltaskid) {
        this.smalltaskandprovideemailid = smalltaskandprovideemailid;
        this.email = email;
        this.smalltaskid = smalltaskid;
    }

    public SmallTaskAndProvidePersonEmail(Long smalltaskandprovideemailid, String email, Integer smalltaskid) {
        this.smalltaskandprovideemailid = Math.toIntExact(smalltaskandprovideemailid);
        this.email = email;
        this.smalltaskid = smalltaskid;
    }

    public SmallTaskAndProvidePersonEmail(String email, Integer smalltaskid) {
        this.email = email;
        this.smalltaskid = smalltaskid;
    }

    public Integer getSmalltaskandprovideemailid() {
        return smalltaskandprovideemailid;
    }

    public void setSmalltaskandprovideemailid(Integer smalltaskandprovideemailid) {
        this.smalltaskandprovideemailid = smalltaskandprovideemailid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getSmalltaskid() {
        return smalltaskid;
    }

    public void setSmalltaskid(Integer smalltaskid) {
        this.smalltaskid = smalltaskid;
    }
}