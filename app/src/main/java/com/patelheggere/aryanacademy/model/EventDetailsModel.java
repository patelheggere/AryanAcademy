package com.patelheggere.aryanacademy.model;

public class EventDetailsModel {
    private String title;
    private String desc;
    private String date;
    private String place;

    public EventDetailsModel() {
    }

    public EventDetailsModel(String title, String desc, String date, String place) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
