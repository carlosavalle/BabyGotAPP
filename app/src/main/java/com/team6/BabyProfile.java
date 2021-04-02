package com.team6;

import java.util.Date;

public class BabyProfile {
    private String id;
    private String Name;
    private Date Birthday;
    private String Gender;
    private String Picture;
    private int Milk;
    private int TummyTime;
    private int Sleep;
    private int Diapers;

    public BabyProfile() {
    }

    public BabyProfile(String id, String name, Date birthday, String gender, String picture, int milk, int tummyTime, int sleep, int diapers) {
        this.id = id;
        Name = name;
        Birthday = birthday;
        Gender = gender;
        Picture = picture;
        Milk = milk;
        TummyTime = tummyTime;
        Sleep = sleep;
        Diapers = diapers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public int getMilk() {
        return Milk;
    }

    public void setMilk(int milk) {
        Milk = milk;
    }

    public int getTummyTime() {
        return TummyTime;
    }

    public void setTummyTime(int tummyTime) {
        TummyTime = tummyTime;
    }

    public int getSleep() {
        return Sleep;
    }

    public void setSleep(int sleep) {
        Sleep = sleep;
    }

    public int getDiapers() {
        return Diapers;
    }

    public void setDiapers(int diapers) {
        Diapers = diapers;
    }
}