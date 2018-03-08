package com.wwdlb.hongruan.pojo;

public class ReceivePersonAndSignNumPojo {
    private String email;
    private String name;
    private int haveSignInDay;


    public ReceivePersonAndSignNumPojo() {
    }

    public ReceivePersonAndSignNumPojo(String email, String name, int haveSignInDay) {
        this.email = email;
        this.name = name;
        this.haveSignInDay = haveSignInDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHaveSignInDay() {
        return haveSignInDay;
    }

    public void setHaveSignInDay(int haveSignInDay) {
        this.haveSignInDay = haveSignInDay;
    }
}
