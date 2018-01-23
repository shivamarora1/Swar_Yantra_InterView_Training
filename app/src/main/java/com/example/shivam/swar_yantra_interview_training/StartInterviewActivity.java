package com.example.shivam.swar_yantra_interview_training;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.swar_yantra_interview_training.data.Interview;
import com.example.shivam.swar_yantra_interview_training.data.InterviewResponse;
import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.db.InterviewDAO;
import com.example.shivam.swar_yantra_interview_training.db.InterviewResponseDAO;
import com.example.shivam.swar_yantra_interview_training.db.QuestionDAO;
import com.example.shivam.swar_yantra_interview_training.utility.AppConstants;
import com.example.shivam.swar_yantra_interview_training.utility.MediaController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static com.example.shivam.swar_yantra_interview_training.utility.AppConstants.QUESTION_AUDIO_PATH;

public class StartInterviewActivity extends AppCompatActivity implements MediaController {//implements MediaController {

    MediaRecorder mMediaRecorder;
    String interviewDate;
    Random mRandom;
    ArrayList<Integer> questionsToAttemptList;
    int numberOfQuestions,counter1=0,questionNumber,interviewId;
    Boolean recordingEnable;
    String soundUrl;
    TextView questionTitle;
    TextView questionHint;
    Button playSound;
    ImageView recordImage;
    MediaPlayer mediaPlayer;
    ArrayList<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test2);

        mediaPlayer=new MediaPlayer();
        questionTitle = (TextView) findViewById(R.id.question_title);
        questionHint = (TextView) findViewById(R.id.question_hint);
        playSound = (Button) findViewById(R.id.play_sound);
        recordImage = (ImageView) findViewById(R.id.record);
        questionList = new QuestionDAO(StartInterviewActivity.this).getAllQuestions();
        Date mDate = new Date();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        interviewDate=mSimpleDateFormat.format(mDate);
        Interview mInterview = new Interview(null, mSimpleDateFormat.format(mDate));
        new InterviewDAO(StartInterviewActivity.this).addInterview(mInterview);
        interviewId=new InterviewDAO(getApplicationContext()).getInterviewId(interviewDate);
        Log.i("Interview Date",interviewId+"");
        mRandom = new Random();
        recordingEnable = true;

        File f = new File(AppConstants.BASE_DEVICE_PATH + interviewDate);
        if (!f.exists()) {
            f.mkdirs();
        }
        fillQuestionsToAttemptList();
        loadQuestions(questionsToAttemptList.get(counter1));

        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop(soundUrl);
            }
        });

        recordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recordingEnable) {
                    mediaRecorderPrepare();
                    recordImage.setBackgroundResource(R.drawable.ic_stop_black_24dp);
                    Toast.makeText(StartInterviewActivity.this, "Speak...", Toast.LENGTH_SHORT).show();
                    recordingEnable = false;
                    try {
                        mMediaRecorder.prepare();
                        mMediaRecorder.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    recordingEnable = true;
                    mediaPlayer.stop();
                    mMediaRecorder.stop();
                    mMediaRecorder.release();
                    mMediaRecorder = null;
                    recordImage.setBackgroundResource(R.drawable.ic_mic_black_24dp);
                    InterviewResponse mInterviewResponse = new InterviewResponse(interviewId, questionsToAttemptList.get(counter1),questionsToAttemptList.get(counter1)+".3gpp" );
                    new InterviewResponseDAO(StartInterviewActivity.this).addInterviewResponse(mInterviewResponse);
                    if(counter1++<questionsToAttemptList.size()-1) {
                        loadQuestions(questionsToAttemptList.get(counter1));
                    }
                    else{
                        Toast.makeText(StartInterviewActivity.this, "Interview Finished...", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(StartInterviewActivity.this, InterviewListActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void fillQuestionsToAttemptList() {
        questionsToAttemptList = new ArrayList<>();
        numberOfQuestions = mRandom.nextInt(questionList.size() - 3) + 3;
        while (numberOfQuestions>0) {
            questionNumber = mRandom.nextInt(questionList.size());
            if (questionsToAttemptList.contains(questionNumber)) {
                questionNumber = mRandom.nextInt(questionList.size());
            } else {
                questionsToAttemptList.add(questionNumber);
                numberOfQuestions--;
            }
        }
    }

    private void mediaRecorderPrepare() {
        mediaPlayer.stop();
        mMediaRecorder = new MediaRecorder();
        String savePathInDevice = AppConstants.BASE_DEVICE_PATH +interviewDate+"/"+questionsToAttemptList.get(counter1)+".3gpp";
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mMediaRecorder.setOutputFile(savePathInDevice);
    }

    public void loadQuestions(int questionNumber) {
        questionTitle.setText(questionList.get(questionNumber).getQuestion());
        questionHint.setText(questionList.get(questionNumber).getQuestionHint());
        soundUrl = QUESTION_AUDIO_PATH+questionList.get(questionNumber).getQuestionSoundUrl();
    }

    public void onBackPressed() {
        Toast.makeText(this, "Please Complete Your Interview....", Toast.LENGTH_SHORT).show();
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
}
