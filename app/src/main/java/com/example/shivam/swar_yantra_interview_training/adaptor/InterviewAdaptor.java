package com.example.shivam.swar_yantra_interview_training.adaptor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shivam.swar_yantra_interview_training.R;
import com.example.shivam.swar_yantra_interview_training.data.Interview;

import java.util.ArrayList;

/**
 * Created by Shivam on 1/8/2018.
 */

public class InterviewAdaptor extends BaseAdapter {
    ArrayList<Interview> interviewList;
    Context mContext;

    public InterviewAdaptor(ArrayList<Interview> interviewList, Context mContext) {
        this.interviewList = interviewList;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return interviewList.size();
    }

    @Override
    public Object getItem(int i) {
        return interviewList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= view.inflate(mContext, R.layout.custom_view_interview,null);
        TextView date_interview=(TextView)v.findViewById(R.id.date_interview);
        date_interview.setText(interviewList.get(i).getInterviewDate());
        return v;
    }
}
