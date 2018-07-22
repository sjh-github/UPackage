package com.wwdlb.hongruan.model;

public class PersonAndSmallTask {
    private Integer id;

    private String email;

    private Integer smalltaskid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public PersonAndSmallTask(String email, Integer smalltaskid) {
        this.email = email;
        this.smalltaskid = smalltaskid;
    }

    public PersonAndSmallTask() {
    }
}