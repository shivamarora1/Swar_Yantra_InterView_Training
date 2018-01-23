package com.example.shivam.swar_yantra_interview_training.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivam on 1/6/2018.
 */

public class Interview {

    int interviewId;
    String interviewName;
    String interviewDate;

    public Interview(int interviewId, String interviewName, String interviewDate) {
        this.interviewId = interviewId;
        this.interviewName = interviewName;
        this.interviewDate = interviewDate;
    }

    public Interview(String interviewName, String interviewDate) {
        this.interviewName = interviewName;
        this.interviewDate = interviewDate;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }
}
