package com.patelheggere.aryanacademy.model;

public class CourseModel {
    private String mEligibilty;
    private String mAgeAttempts;
    private String syllabus;
    private String part1;
    private String part2;
    private String part3;
    private String part4;
    private String part5;
    private String part6;

    public CourseModel() {
    }

    public CourseModel(String mEligibilty, String mAgeAttempts, String syllabus, String part1, String part2, String part3, String part4, String part5, String part6) {
        this.mEligibilty = mEligibilty;
        this.mAgeAttempts = mAgeAttempts;
        this.syllabus = syllabus;
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
        this.part5 = part5;
        this.part6 = part6;
    }

    public String getmEligibilty() {
        return mEligibilty;
    }

    public void setmEligibilty(String mEligibilty) {
        this.mEligibilty = mEligibilty;
    }

    public String getmAgeAttempts() {
        return mAgeAttempts;
    }

    public void setmAgeAttempts(String mAgeAttempts) {
        this.mAgeAttempts = mAgeAttempts;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String getPart3() {
        return part3;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }

    public String getPart4() {
        return part4;
    }

    public void setPart4(String part4) {
        this.part4 = part4;
    }

    public String getPart5() {
        return part5;
    }

    public void setPart5(String part5) {
        this.part5 = part5;
    }

    public String getPart6() {
        return part6;
    }

    public void setPart6(String part6) {
        this.part6 = part6;
    }


}
