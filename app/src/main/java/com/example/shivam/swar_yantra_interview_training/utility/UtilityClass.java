package com.example.shivam.swar_yantra_interview_training.utility;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.data.Questions;
import com.example.shivam.swar_yantra_interview_training.data.Request;
import com.example.shivam.swar_yantra_interview_training.db.QuestionDAO;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.shivam.swar_yantra_interview_training.utility.HttpURLs.GET_AUDIO_ZIP_URL;
import static com.example.shivam.swar_yantra_interview_training.utility.HttpURLs.GET_QUESTION_JSON_URL;

/**
 * Created by Shivam on 1/17/2018.
 */

public class UtilityClass {

    private static final String LOGGING_TAG = "UtilityClass";
    private static Gson gson=new Gson();

    public static void getInterViewQuestion(Context mContext) throws Exception {
        String lastDate = new QuestionDAO(mContext).getMaxQuestionDate();
        Log.i("LastDate:","Last Date is "+lastDate);
        Request mRequest = new Request();
        mRequest.setLastUpdateDate(lastDate);
        String json = gson.toJson(mRequest);
        Log.i("param",json);
        String response = executePost(HttpURLs.GET_QUESTION_JSON_URL, json,200000);
        Log.i("Response:",response);
        if (!response.equals("null")) {
            Questions questions = gson.fromJson(response, Questions.class);
            ArrayList<Question> questionsList = questions.getQuestionsList();
            if(questionsList.size()>0) {
                for (Question q : questionsList) {
                    new QuestionDAO(mContext).addQuestions(q);
                }
            }
        }
    }

    public static void getQuestionAudio(Context mContext) throws Exception {
        String lastDate = new QuestionDAO(mContext).getMaxQuestionDate();
        Request mRequest = new Request();
        mRequest.setLastUpdateDate(lastDate);
        Log.i("RequestAud:", lastDate);
        String json = gson.toJson(mRequest);
        File _zipFile = executePost(HttpURLs.GET_AUDIO_ZIP_URL, json, 200000, new File(AppConstants.QUESTION_AUDIO_ZIP_PATH));
        if(_zipFile.exists()) {
        new Decompress(mContext,_zipFile).unzip();
        }
        else{
            Log.i("File","Not Exists");
        }
    }


    public static String executePost(String targetURL, String urlParameters, int waitingTime) throws Exception {
        Log.d(LOGGING_TAG, targetURL);
        Log.d(LOGGING_TAG, "executePost");
        String result = null;
        HttpURLConnection connection = null;
        try {
            StringBuilder builder = new StringBuilder("jsonString=");
            Log.d(LOGGING_TAG, "target Url " + targetURL + "*****************");
            builder.append(urlParameters);
            Log.d(LOGGING_TAG, "url parameter" + urlParameters + "*****************");
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",Integer.toString(builder.toString().getBytes().length));
            connection.setRequestProperty("Content-Language","en-US");
            connection.setConnectTimeout(waitingTime);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
            writer.write(builder.toString());
            writer.close();
            wr.close();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            result = response.toString().trim();
            Log.d(LOGGING_TAG, "result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    public static File executePost(String targetURL, String urlParameters, int waitingTime, File destination) throws Exception {
        Log.d(LOGGING_TAG, targetURL);
        Log.d(LOGGING_TAG, "executePostForFIleDownlaod...");
        String result = null;
        HttpURLConnection connection = null; ////We can also use URL connection here.....
        try {
            StringBuilder builder = new StringBuilder("jsonString=");
            Log.d(LOGGING_TAG, "target Url " + targetURL + "*****************");
            builder.append(urlParameters);
            Log.d(LOGGING_TAG, "url parameter" + urlParameters + "*****************");
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",Integer.toString(builder.toString().getBytes().length));
            connection.setRequestProperty("Content-Language","en-US");
            connection.setConnectTimeout(waitingTime);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
            writer.write(builder.toString());
            writer.close();
            wr.close();
            int lenghtOfFile = connection.getContentLength();
            Log.i("Size:", lenghtOfFile + "");
            InputStream input = new BufferedInputStream(connection.getInputStream());
            FileOutputStream output = new FileOutputStream(destination);
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
            return destination;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
