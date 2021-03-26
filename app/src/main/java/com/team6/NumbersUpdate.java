package com.team6;


import java.util.Date;

public class NumbersUpdate {
    private int id;
    private String date;
    private String milk;
    private String tummyTime;
    private String sleep;
    private String diapers;

    public NumbersUpdate(int id, String date, String milk, String tummyTime, String sleep, String diapers) {
        this.id = id;
        this.date = date;
        this.milk = milk;
        this.tummyTime = tummyTime;
        this.sleep = sleep;
        this.diapers = diapers;
    }

    public int getID() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMilk() {
        return milk;
    }

    public String getTummyTime() {
        return tummyTime;
    }

    public String getSleep() {
        return sleep;
    }

    public String getDiapers() {
        return diapers;
    }

}
