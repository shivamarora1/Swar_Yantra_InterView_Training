package com.example.shivam.swar_yantra_interview_training.data;

/**
 * Created by Shivam on 1/8/2018.
 */

public class InterviewResponse {
    int interviewResponseId;
    int interviewId;
    int questionId;
    String userSoundUrl;

    public InterviewResponse(int interviewResponseId, int interviewId, int questionId, String userSoundUrl) {
        this.interviewResponseId = interviewResponseId;
        this.interviewId = interviewId;
        this.questionId = questionId;
        this.userSoundUrl = userSoundUrl;
    }

    public InterviewResponse(int interviewId, int questionId, String userSoundUrl) {
        this.interviewId = interviewId;
        this.questionId = questionId;
        this.userSoundUrl = userSoundUrl;
    }

    public int getInterviewResponseId() {
        return interviewResponseId;
    }

    public void setInterviewResponseId(int interviewResponseId) {
        this.interviewResponseId = interviewResponseId;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getUserSoundUrl() {
        return userSoundUrl;
    }

    public void setUserSoundUrl(String userSoundUrl) {
        this.userSoundUrl = userSoundUrl;
    }
}
