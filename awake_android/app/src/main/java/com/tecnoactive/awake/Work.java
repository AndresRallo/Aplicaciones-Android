package com.tecnoactive.awake;


public class Work {

    private String date;
    private String time;
    private String title;
    private Boolean check;

    public Work(String date, String time, String title) {
        this.date = date;
        this.time = time;
        this.title = title;
        this.check = false;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
