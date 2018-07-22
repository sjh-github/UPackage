package com.wwdlb.hongruan.pojo;

public class ReceivePersonalInformationPojo {
    private String name;
    private String phone;
    private String gender;
    private String todaySignInTime;
    private int haveSignInDay;

    public ReceivePersonalInformationPojo() {
    }

    public ReceivePersonalInformationPojo(String name, String phone, String gender, String todaySignInTime, int haveSignInDay) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.todaySignInTime = todaySignInTime;
        this.haveSignInDay = haveSignInDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTodaySignInTime() {
        return todaySignInTime;
    }

    public void setTodaySignInTime(String todaySignInTime) {
        this.todaySignInTime = todaySignInTime;
    }

    public int getHaveSignInDay() {
        return haveSignInDay;
    }

    public void setHaveSignInDay(int haveSignInDay) {
        this.haveSignInDay = haveSignInDay;
    }
}
