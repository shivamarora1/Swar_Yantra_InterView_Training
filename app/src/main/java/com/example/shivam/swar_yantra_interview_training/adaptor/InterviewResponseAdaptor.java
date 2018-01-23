package com.example.shivam.swar_yantra_interview_training.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.swar_yantra_interview_training.R;
import com.example.shivam.swar_yantra_interview_training.data.InterviewResponse;
import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.db.QuestionDAO;
import com.example.shivam.swar_yantra_interview_training.utility.MediaController;

import java.util.ArrayList;

/**
 * Created by Shivam on 1/8/2018.
 */

public class InterviewResponseAdaptor extends BaseAdapter {
    ArrayList<InterviewResponse> interviewResponseList;
    Context mContext;
    MediaController mediaController;

    public InterviewResponseAdaptor(ArrayList<InterviewResponse> interviewResponseList, Context mContext,MediaController mediaController) {
        this.interviewResponseList = interviewResponseList;
        this.mContext = mContext;
        this.mediaController=mediaController;
    }

    @Override
    public int getCount() {
        return interviewResponseList.size();
    }

    @Override
    public Object getItem(int i) {
        return interviewResponseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(mContext, R.layout.custom_view_interview_response,null);
        TextView response_text=(TextView)v.findViewById(R.id.response_text);
        ImageView play_response=(ImageView)v.findViewById(R.id.play_response);
        String questionTitle=interviewResponseList.get(i).getQuestionId()+"";
        Question question=new QuestionDAO(mContext).getQuestion(Integer.parseInt(questionTitle));
        response_text.setText(question.getQuestion());
        play_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaController.startStop(interviewResponseList.get(i).getUserSoundUrl());
            }
        });
        return v;
    }
}
