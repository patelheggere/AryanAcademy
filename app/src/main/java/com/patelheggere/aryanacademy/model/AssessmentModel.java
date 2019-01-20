package com.patelheggere.aryanacademy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AssessmentModel implements Parcelable {
    private int id;
    private int marksSecured;
    private int noOfCorrectAns;
    private int noOfQuestions;
    private int noOfWrongAns;
    private int totalMarks;
    private boolean attemptedAllowed;
    private boolean shuffle;
    private long mTime;
    private String mTitle;
    private boolean completed;
    private int maxReattempt;
    private long endTime;

    public AssessmentModel() {
    }

    public AssessmentModel(int id, int marksSecured, int noOfCorrectAns, int noOfQuestions, int noOfWrongAns, int totalMarks, boolean attemptedAllowed, boolean shuffle, long mTime, String mTitle, boolean completed, int maxReattempt, long endTime) {
        this.id = id;
        this.marksSecured = marksSecured;
        this.noOfCorrectAns = noOfCorrectAns;
        this.noOfQuestions = noOfQuestions;
        this.noOfWrongAns = noOfWrongAns;
        this.totalMarks = totalMarks;
        this.attemptedAllowed = attemptedAllowed;
        this.shuffle = shuffle;
        this.mTime = mTime;
        this.mTitle = mTitle;
        this.completed = completed;
        this.maxReattempt = maxReattempt;
        this.endTime = endTime;
    }

    protected AssessmentModel(Parcel in) {
        id = in.readInt();
        marksSecured = in.readInt();
        noOfCorrectAns = in.readInt();
        noOfQuestions = in.readInt();
        noOfWrongAns = in.readInt();
        totalMarks = in.readInt();
        attemptedAllowed = in.readByte() != 0;
        shuffle = in.readByte() != 0;
        mTime = in.readLong();
        mTitle = in.readString();
        completed = in.readByte() != 0;
        maxReattempt = in.readInt();
        endTime = in.readLong();
    }

    public static final Creator<AssessmentModel> CREATOR = new Creator<AssessmentModel>() {
        @Override
        public AssessmentModel createFromParcel(Parcel in) {
            return new AssessmentModel(in);
        }

        @Override
        public AssessmentModel[] newArray(int size) {
            return new AssessmentModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(marksSecured);
        parcel.writeInt(noOfCorrectAns);
        parcel.writeInt(noOfQuestions);
        parcel.writeInt(noOfWrongAns);
        parcel.writeInt(totalMarks);
        parcel.writeByte((byte) (attemptedAllowed ? 1 : 0));
        parcel.writeByte((byte) (shuffle ? 1 : 0));
        parcel.writeLong(mTime);
        parcel.writeString(mTitle);
        parcel.writeByte((byte) (completed ? 1 : 0));
        parcel.writeInt(maxReattempt);
        parcel.writeLong(endTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMarksSecured() {
        return marksSecured;
    }

    public void setMarksSecured(int marksSecured) {
        this.marksSecured = marksSecured;
    }

    public int getNoOfCorrectAns() {
        return noOfCorrectAns;
    }

    public void setNoOfCorrectAns(int noOfCorrectAns) {
        this.noOfCorrectAns = noOfCorrectAns;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public int getNoOfWrongAns() {
        return noOfWrongAns;
    }

    public void setNoOfWrongAns(int noOfWrongAns) {
        this.noOfWrongAns = noOfWrongAns;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public boolean isAttemptedAllowed() {
        return attemptedAllowed;
    }

    public void setAttemptedAllowed(boolean attemptedAllowed) {
        this.attemptedAllowed = attemptedAllowed;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getMaxReattempt() {
        return maxReattempt;
    }

    public void setMaxReattempt(int maxReattempt) {
        this.maxReattempt = maxReattempt;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public static Creator<AssessmentModel> getCREATOR() {
        return CREATOR;
    }
}
