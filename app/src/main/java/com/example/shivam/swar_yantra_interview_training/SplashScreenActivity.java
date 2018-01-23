package com.example.shivam.swar_yantra_interview_training;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.shivam.swar_yantra_interview_training.utility.AlarmReceiver;
import com.example.shivam.swar_yantra_interview_training.utility.AppConstants;
import com.example.shivam.swar_yantra_interview_training.utility.UtilityClass;

import java.io.File;
import java.net.InetAddress;

import io.github.skyhacker2.sqliteonweb.SQLiteOnWeb;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("Loading, Please Wait...");
        mProgressDialog.show();

        AlarmManager alarmManager=(AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(),60000,pendingIntent);

        File f = new File(AppConstants.QUESTION_AUDIO_PATH);
        if (!f.exists()) {
            f.mkdirs();
        }
        if(isConnectedToInternet()) {
            if (!checkpermission()) {
                requestPermission();
            }
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mProgressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(),InterviewListActivity.class));
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try{
                    UtilityClass.getQuestionAudio(getApplicationContext());
                    UtilityClass.getInterViewQuestion(getApplicationContext());
                }catch (Exception e)
                {
                 e.printStackTrace();
                }finally {
                    return null;
                }
            }
        }.execute();

        SQLiteOnWeb.init(this).start(); ///This is for viewing SALite on web....
    }

    private boolean isConnectedToInternet() {
        try{
            ConnectivityManager mConnectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            InetAddress mInetAddress=InetAddress.getByName("google.com");
            Log.i("Internet:", String.valueOf(mConnectivityManager.getActiveNetworkInfo()!=null && !mInetAddress.equals("")));
            return mConnectivityManager.getActiveNetworkInfo()!=null && !mInetAddress.equals("");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{WRITE_EXTERNAL_STORAGE,RECORD_AUDIO},1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted...", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Not Granted...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkpermission() {
        int result1= ContextCompat.checkSelfPermission(this,WRITE_EXTERNAL_STORAGE);
        int result2=ContextCompat.checkSelfPermission(this,RECORD_AUDIO);
        return result1== PackageManager.PERMISSION_GRANTED && result2==PackageManager.PERMISSION_GRANTED;
    }
}
