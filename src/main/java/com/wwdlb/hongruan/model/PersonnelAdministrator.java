package com.wwdlb.hongruan.model;

public class PersonnelAdministrator {
    private String email;

    private String password;

    private String name;

    private Integer gender;

    private Integer birthyear;

    private Integer birthmonth;

    private Integer birthday;

    private String idcard;

    private String phone;

    private String idfile;

    private String havechecked;

    private byte[] photodata;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Integer birthyear) {
        this.birthyear = birthyear;
    }

    public Integer getBirthmonth() {
        return birthmonth;
    }

    public void setBirthmonth(Integer birthmonth) {
        this.birthmonth = birthmonth;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdfile() {
        return idfile;
    }

    public void setIdfile(String idfile) {
        this.idfile = idfile == null ? null : idfile.trim();
    }

    public String getHavechecked() {
        return havechecked;
    }

    public void setHavechecked(String havechecked) {
        this.havechecked = havechecked == null ? null : havechecked.trim();
    }

    public byte[] getPhotodata() {
        return photodata;
    }

    public void setPhotodata(byte[] photodata) {
        this.photodata = photodata;
    }
}