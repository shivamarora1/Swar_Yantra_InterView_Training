package com.example.shivam.swar_yantra_interview_training.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
/**
 * Created by Shivam on 1/21/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        try{
           new AsyncTask<Void,Void,Void>(){
               @Override
               protected Void doInBackground(Void... voids) {
                   try {
                       UtilityClass.getQuestionAudio(context);
                       UtilityClass.getInterViewQuestion(context);
                       Log.i("Thread ","Started...");
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   return null;
               }
           }.execute();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
