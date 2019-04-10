package com.patelheggere.aryanacademy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AssessmentModel implements Parcelable {
    private int id;
    private int marksSecured;
    private int noOfCorrectAns;
    private int NoOfQuestions;
    private int noOfWrongAns;
    private int TotalMarks;
    private boolean isAttemptedAllowed;
    private boolean isShuffle;
    private long mTime;
    private String mTitle;
    private boolean completed;
    private int maxReattempt;
    private long endTime;
    private String assessmentId;
    private String questions;
    private String userAssessmentId;

    public AssessmentModel() {
    }

    public AssessmentModel(String userAssessmentId, int id, int marksSecured, int noOfCorrectAns, int NoOfQuestions, int noOfWrongAns, int TotalMarks, boolean isAttemptedAllowed, boolean isShuffle, long mTime, String mTitle, boolean completed, int maxReattempt, long endTime, String assessmentId, String questions) {
        this.id = id;
        this.marksSecured = marksSecured;
        this.noOfCorrectAns = noOfCorrectAns;
        this.NoOfQuestions = NoOfQuestions;
        this.noOfWrongAns = noOfWrongAns;
        this.TotalMarks = TotalMarks;
        this.isAttemptedAllowed = isAttemptedAllowed;
        this.isShuffle = isShuffle;
        this.mTime = mTime;
        this.mTitle = mTitle;
        this.completed = completed;
        this.maxReattempt = maxReattempt;
        this.endTime = endTime;
        this.assessmentId = assessmentId;
        this.questions = questions;
        this.userAssessmentId = userAssessmentId;
    }

    protected AssessmentModel(Parcel in) {
        id = in.readInt();
        marksSecured = in.readInt();
        noOfCorrectAns = in.readInt();
        NoOfQuestions = in.readInt();
        noOfWrongAns = in.readInt();
        TotalMarks = in.readInt();
        isAttemptedAllowed = in.readByte() != 0;
        isShuffle = in.readByte() != 0;
        mTime = in.readLong();
        mTitle = in.readString();
        completed = in.readByte() != 0;
        maxReattempt = in.readInt();
        endTime = in.readLong();
        assessmentId = in.readString();
        questions = in.readString();
        userAssessmentId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(marksSecured);
        dest.writeInt(noOfCorrectAns);
        dest.writeInt(NoOfQuestions);
        dest.writeInt(noOfWrongAns);
        dest.writeInt(TotalMarks);
        dest.writeByte((byte) (isAttemptedAllowed ? 1 : 0));
        dest.writeByte((byte) (isShuffle ? 1 : 0));
        dest.writeLong(mTime);
        dest.writeString(mTitle);
        dest.writeByte((byte) (completed ? 1 : 0));
        dest.writeInt(maxReattempt);
        dest.writeLong(endTime);
        dest.writeString(assessmentId);
        dest.writeString(questions);
        dest.writeString(userAssessmentId);
    }

    @Override
    public int describeContents() {
        return 0;
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
        return NoOfQuestions;
    }

    public void setNoOfQuestions(int NoOfQuestions) {
        this.NoOfQuestions = NoOfQuestions;
    }

    public int getNoOfWrongAns() {
        return noOfWrongAns;
    }

    public void setNoOfWrongAns(int noOfWrongAns) {
        this.noOfWrongAns = noOfWrongAns;
    }

    public int getTotalMarks() {
        return TotalMarks;
    }

    public void setTotalMarks(int TotalMarks) {
        this.TotalMarks = TotalMarks;
    }

    public boolean isisAttemptedAllowed() {
        return isAttemptedAllowed;
    }

    public void setisAttemptedAllowed(boolean isAttemptedAllowed) {
        this.isAttemptedAllowed = isAttemptedAllowed;
    }

    public boolean isisShuffle() {
        return isShuffle;
    }

    public void setisShuffle(boolean isShuffle) {
        this.isShuffle = isShuffle;
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

    public String getAssessmentId() {
        return assessmentId;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public static Creator<AssessmentModel> getCREATOR() {
        return CREATOR;
    }

    public boolean isAttemptedAllowed() {
        return isAttemptedAllowed;
    }

    public void setAttemptedAllowed(boolean attemptedAllowed) {
        isAttemptedAllowed = attemptedAllowed;
    }

    public boolean isShuffle() {
        return isShuffle;
    }

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    public String getUserAssessmentId() {
        return userAssessmentId;
    }

    public void setUserAssessmentId(String userAssessmentId) {
        this.userAssessmentId = userAssessmentId;
    }
}
