package com.patelheggere.aryanacademy.model;

public class Data {
    private String NAME;
    private String EMAIL;

    public Data() {
    }

    public Data(String NAME, String EMAIL) {
        this.NAME = NAME;
        this.EMAIL = EMAIL;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
}
