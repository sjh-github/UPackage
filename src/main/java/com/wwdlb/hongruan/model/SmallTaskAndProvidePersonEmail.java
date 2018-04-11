package com.wwdlb.hongruan.model;

public class SmallTaskAndProvidePersonEmail {
    private String email;

    private Integer smalltaskid;

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

    public SmallTaskAndProvidePersonEmail(String email, Integer smalltaskid) {
        this.email = email;
        this.smalltaskid = smalltaskid;
    }

    public SmallTaskAndProvidePersonEmail() {
    }
}