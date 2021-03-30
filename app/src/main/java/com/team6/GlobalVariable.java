package com.team6;

import android.app.Application;


//this class will be user to share variable between classes from BabyProfileAdapter .onClick

public class GlobalVariable extends Application {
//Baby profile id
    private String idProfile;

    public String getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(String idProfile) {
        this.idProfile = idProfile;
    }
}
