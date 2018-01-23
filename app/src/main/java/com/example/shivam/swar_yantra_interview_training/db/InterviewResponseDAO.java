package com.example.shivam.swar_yantra_interview_training.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shivam.swar_yantra_interview_training.data.InterviewResponse;
import com.example.shivam.swar_yantra_interview_training.utility.DBHelper;

import java.util.ArrayList;

import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_RESPONSE_TABLE;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_RESPONSE_TABLE_ID;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_RESPONSE_TABLE_INTERVIEW_ID;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_RESPONSE_TABLE_QUESTION_ID;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_RESPONSE_TABLE_SOUND_URL;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_TABLE_ID;
/**
 * Created by Shivam on 1/5/2018.
 */

//Used for insertion and deletion in the Question table;
public class InterviewResponseDAO {
    private Context mContext;
    private SQLiteDatabase mSqliteDatabase;
    private DBHelper mDBHandler;

    public InterviewResponseDAO(Context mContext)
    {
        this.mContext=mContext;
        mDBHandler=new DBHelper(mContext);
    }

    public void writableOpen() throws SQLException
    {
        mSqliteDatabase=mDBHandler.getWritableDatabase();
    }

    public void readableOpen()
    {
        mSqliteDatabase=mDBHandler.getReadableDatabase();
    }

    public void addInterviewResponse(InterviewResponse mInterviewResponse)
    {
        try {
            writableOpen();
            ContentValues c = new ContentValues();
            c.put(DB_INTERVIEW_RESPONSE_TABLE_INTERVIEW_ID, mInterviewResponse.getInterviewId());
            c.put(DB_INTERVIEW_RESPONSE_TABLE_QUESTION_ID, mInterviewResponse.getQuestionId());
            c.put(DB_INTERVIEW_RESPONSE_TABLE_SOUND_URL, mInterviewResponse.getUserSoundUrl());
            mSqliteDatabase.insert(DB_INTERVIEW_RESPONSE_TABLE, null, c);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
    }

    public ArrayList<InterviewResponse> getAllInterviewResponses(){
        try {
            writableOpen();
            ArrayList<InterviewResponse> allInterviewResponseList = new ArrayList<>();
            String query = "SELECT * FROM " + DB_INTERVIEW_RESPONSE_TABLE;
            Cursor c = mSqliteDatabase.rawQuery(query, null);
            if (c != null && c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        Integer id = c.getInt(0);
                        Integer interviewId = c.getInt(1);
                        Integer questionId = c.getInt(2);
                        String userSoundUrl = c.getString(3);
                        allInterviewResponseList.add(new InterviewResponse(id, interviewId, questionId, userSoundUrl));
                    } while (c.moveToNext());
                }
                return allInterviewResponseList;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
        return null;
    }

    public InterviewResponse getInterviewResponse(String mResponseDateTime)
    {
        try {
            writableOpen();
            Cursor cursor = mSqliteDatabase.query(DB_INTERVIEW_RESPONSE_TABLE, new String[]{DB_INTERVIEW_RESPONSE_TABLE_ID,DB_INTERVIEW_RESPONSE_TABLE_INTERVIEW_ID, DB_INTERVIEW_RESPONSE_TABLE_QUESTION_ID, DB_INTERVIEW_RESPONSE_TABLE_SOUND_URL}, DB_INTERVIEW_RESPONSE_TABLE_ID + "=?",
                    new String[]{mResponseDateTime}, null, null, null, null);
            if(cursor!=null && cursor.getCount()>0) {
                if (cursor != null) {
                    cursor.moveToFirst();
                    InterviewResponse mInterviewResponse = new InterviewResponse(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), cursor.getString(3));
                    return mInterviewResponse;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
        return null;
    }


    public ArrayList<InterviewResponse> getParticularInterviewResponse(int interviewId) {
        ArrayList<InterviewResponse> particularInterviewResponseList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_INTERVIEW_RESPONSE_TABLE + " WHERE "+DB_INTERVIEW_RESPONSE_TABLE_INTERVIEW_ID + "=" + interviewId ;
        try {
            writableOpen();
            Cursor c = mSqliteDatabase.rawQuery(query, null);
            if (c != null && c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        Integer id = c.getInt(0);
                        Integer mInterviewId = c.getInt(1);
                        Integer mQuestionId = c.getInt(2);
                        String mUserSoundUrl = c.getString(3);
                        particularInterviewResponseList.add(new InterviewResponse(id, mInterviewId, mQuestionId, mUserSoundUrl));
                    } while (c.moveToNext());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
        return particularInterviewResponseList;
    }
}
