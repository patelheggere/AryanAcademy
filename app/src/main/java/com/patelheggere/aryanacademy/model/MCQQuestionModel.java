package com.patelheggere.aryanacademy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MCQQuestionModel implements Parcelable {
    private int mQuestionNo;
    private String question;
    private int correctAns;
    private boolean isAnswered;
    private boolean isViewed;
    private int mNoOfOptions;
    private int mQType;
    private int mUserAnswer = -1;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;


    public MCQQuestionModel() {
    }

    public MCQQuestionModel(int mQuestionNo, String question, int correctAns, boolean isAnswered, boolean isViewed, int mNoOfOptions, int mQType, int mUserAnswer, String option1, String option2, String option3, String option4, String option5) {
        this.mQuestionNo = mQuestionNo;
        this.question = question;
        this.correctAns = correctAns;
        this.isAnswered = isAnswered;
        this.isViewed = isViewed;
        this.mNoOfOptions = mNoOfOptions;
        this.mQType = mQType;
        this.mUserAnswer = mUserAnswer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
    }

    protected MCQQuestionModel(Parcel in) {
        mQuestionNo = in.readInt();
        question = in.readString();
        correctAns = in.readInt();
        isAnswered = in.readByte() != 0;
        isViewed = in.readByte() != 0;
        mNoOfOptions = in.readInt();
        mQType = in.readInt();
        mUserAnswer = in.readInt();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        option5 = in.readString();
    }

    public static final Creator<MCQQuestionModel> CREATOR = new Creator<MCQQuestionModel>() {
        @Override
        public MCQQuestionModel createFromParcel(Parcel in) {
            return new MCQQuestionModel(in);
        }

        @Override
        public MCQQuestionModel[] newArray(int size) {
            return new MCQQuestionModel[size];
        }
    };

    public int getmQuestionNo() {
        return mQuestionNo;
    }

    public void setmQuestionNo(int mQuestionNo) {
        this.mQuestionNo = mQuestionNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    public int getmNoOfOptions() {
        return mNoOfOptions;
    }

    public void setmNoOfOptions(int mNoOfOptions) {
        this.mNoOfOptions = mNoOfOptions;
    }

    public int getmQType() {
        return mQType;
    }

    public void setmQType(int mQType) {
        this.mQType = mQType;
    }

    public int getmUserAnswer() {
        return mUserAnswer;
    }

    public void setmUserAnswer(int mUserAnswer) {
        this.mUserAnswer = mUserAnswer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mQuestionNo);
        parcel.writeString(question);
        parcel.writeInt(correctAns);
        parcel.writeByte((byte) (isAnswered ? 1 : 0));
        parcel.writeByte((byte) (isViewed ? 1 : 0));
        parcel.writeInt(mNoOfOptions);
        parcel.writeInt(mQType);
        parcel.writeInt(mUserAnswer);
        parcel.writeString(option1);
        parcel.writeString(option2);
        parcel.writeString(option3);
        parcel.writeString(option4);
        parcel.writeString(option5);
    }
}
