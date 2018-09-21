package com.patelheggere.aryanacademy.model;

public class JobUpdatesModel {
    private String title;
    private String dept;
    private String lastDate;
    private String examDate;
    private String qualification;
    private String notiDate;
    private String examMode;
    private String paymentMode;
    private String documents;
    private String noPosts;
    private String website;

    public JobUpdatesModel() {
    }

    public JobUpdatesModel(String title, String dept, String lastDate, String examDate, String qualification, String notiDate, String examMode, String paymentMode, String documents, String noPosts, String website) {
        this.title = title;
        this.dept = dept;
        this.lastDate = lastDate;
        this.examDate = examDate;
        this.qualification = qualification;
        this.notiDate = notiDate;
        this.examMode = examMode;
        this.paymentMode = paymentMode;
        this.documents = documents;
        this.noPosts = noPosts;
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
    }

    public String getExamMode() {
        return examMode;
    }

    public void setExamMode(String examMode) {
        this.examMode = examMode;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getNoPosts() {
        return noPosts;
    }

    public void setNoPosts(String noPosts) {
        this.noPosts = noPosts;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
