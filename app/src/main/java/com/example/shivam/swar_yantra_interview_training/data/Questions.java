package com.example.shivam.swar_yantra_interview_training.data;

import java.util.ArrayList;

/**
 * Created by Shivam on 1/19/2018.
 */

public class Questions {
    ArrayList<Question> questions;

    public Questions(ArrayList<Question> questionsList) {
        this.questions = questionsList;
    }

    public ArrayList<Question> getQuestionsList() {
        return questions;
    }

    public void setQuestionsList(ArrayList<Question> questionsList) {
        this.questions = questionsList;
    }
}
