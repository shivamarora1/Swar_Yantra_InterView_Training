package com.example.shivam.swar_yantra_interview_training;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.swar_yantra_interview_training.adaptor.InterviewResponseAdaptor;
import com.example.shivam.swar_yantra_interview_training.data.InterviewResponse;
import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.db.InterviewDAO;
import com.example.shivam.swar_yantra_interview_training.db.InterviewResponseDAO;
import com.example.shivam.swar_yantra_interview_training.db.QuestionDAO;
import com.example.shivam.swar_yantra_interview_training.utility.AppConstants;
import com.example.shivam.swar_yantra_interview_training.utility.MediaController;

import java.util.ArrayList;

public class InterviewResponseActivity extends AppCompatActivity implements MediaController {

    String interviewDate;
    TextView interview_date;
    ListView interviewResponseListView;
    MediaPlayer mp;
    InterviewResponseAdaptor interviewResponseAdaptor;
    ArrayList<InterviewResponse> interviewResponseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        interviewDate=getIntent().getStringExtra("interviewDate");
        interview_date=(TextView)findViewById(R.id.interview_date);
        mp = new MediaPlayer();
        interview_date.setText("Interview on: "+interviewDate);
        interviewResponseListView=(ListView)findViewById(R.id.session_list);
        int interviewId=new InterviewDAO(getApplicationContext()).getInterviewId(interviewDate);
        interviewResponseArrayList=new InterviewResponseDAO(InterviewResponseActivity.this).getParticularInterviewResponse(interviewId);
        interviewResponseAdaptor=new InterviewResponseAdaptor(interviewResponseArrayList,InterviewResponseActivity.this,this);
        interviewResponseListView.setAdapter(interviewResponseAdaptor);
    }

    public void onBackPressed()
    {
        mp.stop();
        startActivity(new Intent(InterviewResponseActivity.this,InterviewListActivity.class));
        finish();
    }

    @Override
    public void startStop(String path) {
        path= AppConstants.BASE_DEVICE_PATH+interviewDate+"/"+path;
        try {
                mp.reset();
                mp.setDataSource(path);
                mp.prepare();
                mp.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
