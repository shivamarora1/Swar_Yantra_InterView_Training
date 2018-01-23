package com.example.shivam.swar_yantra_interview_training.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shivam on 1/5/2018.
 */

public class Question {
    private int questionId;
    private String question;
    private String questionHint;
    private String questionSoundUrl;
    private String questionCreatedAt;
    private String questionUpdatedAt;

    public Question(int questionId, String question, String questionHint, String questionSoundUrl, String questionCreatedAt, String questionUpdatedAt) {
        this.questionId = questionId;
        this.question = question;
        this.questionHint = questionHint;
        this.questionSoundUrl = questionSoundUrl;
        this.questionCreatedAt = questionCreatedAt;
        this.questionUpdatedAt = questionUpdatedAt;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionHint() {
        return questionHint;
    }

    public void setQuestionHint(String questionHint) {
        this.questionHint = questionHint;
    }

    public String getQuestionSoundUrl() {
        return questionSoundUrl;
    }

    public void setQuestionSoundUrl(String questionSoundUrl) {
        this.questionSoundUrl = questionSoundUrl;
    }

    public String getQuestionCreatedAt() {
        return questionCreatedAt;
    }

    public void setQuestionCreatedAt(String questionCreatedAt) {
        this.questionCreatedAt = questionCreatedAt;
    }

    public String getQuestionUpdatedAt() {
        return questionUpdatedAt;
    }

    public void setQuestionUpdatedAt(String questionUpdatedAt) {
        this.questionUpdatedAt = questionUpdatedAt;
    }
}
