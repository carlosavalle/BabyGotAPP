package com.team6;

import java.util.Date;

public class BabyProfile {
    private String id;
    private String Name;
    private Date Birthday;
    private String Gender;
    private String Picture;

    public BabyProfile() {
    }

    public BabyProfile(String id, String name, Date birthday, String gender, String picture) {
        this.id = id;
        Name = name;
        Birthday = birthday;
        Gender = gender;
        Picture = picture;
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
}