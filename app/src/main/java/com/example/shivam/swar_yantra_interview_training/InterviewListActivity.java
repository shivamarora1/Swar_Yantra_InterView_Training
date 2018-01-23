package com.example.shivam.swar_yantra_interview_training;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shivam.swar_yantra_interview_training.adaptor.InterviewAdaptor;
import com.example.shivam.swar_yantra_interview_training.data.Interview;
import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.db.InterviewDAO;
import com.example.shivam.swar_yantra_interview_training.db.QuestionDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InterviewListActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigationView;
    ListView session_list;
    TextView empty_message;
    InterviewAdaptor interviewHistoryAdapter;
    ArrayList<Interview> interviewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_list);
        empty_message=(TextView)findViewById(R.id.empty_message);
        session_list = (ListView) findViewById(R.id.session_list);
        interviewList = new InterviewDAO(this).getAllInterviews();
        if(interviewList.size()==0){
            empty_message.setVisibility(View.VISIBLE);
        }
        setUpBottomNavigation();
        interviewHistoryAdapter = new InterviewAdaptor(interviewList, InterviewListActivity.this);
        session_list.setAdapter(interviewHistoryAdapter);
        session_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String mDateTime = interviewList.get(i).getInterviewDate();
                Intent mIntent = new Intent(InterviewListActivity.this, InterviewResponseActivity.class);
                mIntent.putExtra("interviewDate", mDateTime);
                startActivity(mIntent);
            }
        });
    }

    private void setUpBottomNavigation() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.mBottomNavigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.take_interview: {
                        Intent i = new Intent(InterviewListActivity.this, StartInterviewActivity.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.hr_questions: {
                        Intent i = new Intent(new Intent(InterviewListActivity.this, AllQuestionsActivity.class));
                        startActivity(i);
                        break;
                    }
                }
                return false;
            }
        });
    }
    public void onBackPressed() {
        finishAffinity();
    }
}
