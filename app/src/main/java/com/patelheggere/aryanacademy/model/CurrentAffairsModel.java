package com.patelheggere.aryanacademy.model;

public class CurrentAffairsModel {
    private String message;

    public CurrentAffairsModel() {
    }

    public CurrentAffairsModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
