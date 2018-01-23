package com.example.shivam.swar_yantra_interview_training.adaptor;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.swar_yantra_interview_training.R;
import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.utility.AppConstants;
import com.example.shivam.swar_yantra_interview_training.utility.MediaController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Shivam on 1/5/2018.
 */

public class QuestionAdaptor extends BaseAdapter {

    List<Question> questionList;
    Context mContext;
    MediaController mMediaController;

    public QuestionAdaptor(List<Question> list_question, Context mContext, MediaController mMediaController) {
        this.questionList = list_question;
        this.mContext = mContext;
        this.mMediaController=mMediaController;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int i) {
        return questionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(mContext, R.layout.custom_view_question,null);
        TextView titleQues=(TextView)v.findViewById(R.id.title_quest);
        TextView hintQuest=(TextView)v.findViewById(R.id.hint_quest);
        Button playButt=(Button)v.findViewById(R.id.play_ques);
        titleQues.setText(questionList.get(i).getQuestion());
        hintQuest.setText(questionList.get(i).getQuestionHint());

        playButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMediaController.startStop(AppConstants.QUESTION_AUDIO_PATH+questionList.get(i).getQuestionSoundUrl());
            }
        });
        return v;
    }
}
