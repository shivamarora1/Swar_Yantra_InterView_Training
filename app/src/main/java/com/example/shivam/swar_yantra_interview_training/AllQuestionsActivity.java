package com.example.shivam.swar_yantra_interview_training;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shivam.swar_yantra_interview_training.adaptor.QuestionAdaptor;
import com.example.shivam.swar_yantra_interview_training.data.Interview;
import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.db.InterviewDAO;
import com.example.shivam.swar_yantra_interview_training.db.QuestionDAO;
import com.example.shivam.swar_yantra_interview_training.utility.MediaController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllQuestionsActivity extends AppCompatActivity implements MediaController {

    ListView questionListView;
    Button nextButton;
    MediaPlayer mediaPlayer;
    QuestionAdaptor questionAdaptor;
    ArrayList<Question> questionArrayList;
    TextView empty_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=new MediaPlayer();
        questionArrayList=new QuestionDAO(AllQuestionsActivity.this).getAllQuestions();
        questionListView=(ListView)findViewById(R.id.question_list);
        empty_message=(TextView)findViewById(R.id.empty_message);
        nextButton=(Button)findViewById(R.id.next_Button);
        if(questionArrayList.size()==0){
            nextButton.setEnabled(false);
            empty_message.setVisibility(View.VISIBLE);
        }else {
            questionAdaptor = new QuestionAdaptor(questionArrayList, this,this);
            questionListView.setAdapter(questionAdaptor);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AllQuestionsActivity.this, StartInterviewActivity.class);
                    startActivity(i);
                }
            });
        }
    }

    public void onBackPressed()
    {
        finish();
        mediaPlayer.stop();
        startActivity(new Intent(AllQuestionsActivity.this,InterviewListActivity.class));
    }

    @Override
    public void startStop(String path) {
        try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        mediaPlayer.stop();
        super.onPause();
    }
}
