package com.example.shivam.swar_yantra_interview_training.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shivam.swar_yantra_interview_training.data.Question;
import com.example.shivam.swar_yantra_interview_training.utility.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_QUESTION_TABLE;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_QUESTION_TABLE_CREATED_AT;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_QUESTION_TABLE_HINT;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_QUESTION_TABLE_ID;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_QUESTION_TABLE_QUESTION;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_QUESTION_TABLE_SOUND_URL;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_QUESTION_TABLE_UPDATED_AT;
/**
 * Created by Shivam on 1/5/2018.
 */
//Used for insertion and deletion in the Question table;
public class QuestionDAO {
    private Context mContext;
    public static String TAG="SQL ";
    private SQLiteDatabase mSqliteDatabase;
    private DBHelper mDBHandler;  //There is probability of error in this...

    public QuestionDAO(Context mContext)
    {
        this.mContext = mContext;
        mDBHandler = new DBHelper(mContext);
    }

    public void writableOpen()
    {
        mSqliteDatabase=mDBHandler.getWritableDatabase();
    }

    public void readableOpen()
    {
        mSqliteDatabase=mDBHandler.getReadableDatabase();
    }

    public void addQuestions(Question mQuestion)
    {
        try {
            writableOpen();
            ContentValues c = new ContentValues();
            c.put(DB_QUESTION_TABLE_ID, mQuestion.getQuestionId());
            c.put(DB_QUESTION_TABLE_QUESTION, mQuestion.getQuestion());
            c.put(DB_QUESTION_TABLE_HINT, mQuestion.getQuestionHint());
            c.put(DB_QUESTION_TABLE_SOUND_URL, mQuestion.getQuestionSoundUrl());
            c.put(DB_QUESTION_TABLE_CREATED_AT, mQuestion.getQuestionCreatedAt());
            c.put(DB_QUESTION_TABLE_UPDATED_AT, mQuestion.getQuestionUpdatedAt());
            mSqliteDatabase.insert(DB_QUESTION_TABLE, null, c);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
    }

    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> allQuestionList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_QUESTION_TABLE;
        try {
            readableOpen();
            Cursor c = mSqliteDatabase.rawQuery(query, null);
            if (c!=null && c.getCount() != 0 ) {
                if (c.moveToFirst()) {
                    do {
                        Integer id = c.getInt(0);
                        String question = c.getString(1);
                        String questionHint = c.getString(2);
                        String questionSoundUrl = c.getString(3);
                        String questionCreatedAt = c.getString(4);
                        String questionUpdatedAt = c.getString(5);
                        allQuestionList.add(new Question(id, question, questionHint,questionSoundUrl, questionCreatedAt,questionUpdatedAt));
                    } while (c.moveToNext());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
        return allQuestionList;
    }

    public String getMaxQuestionDate(){
        String date="";
        String query = "SELECT MAX("+DB_QUESTION_TABLE_CREATED_AT+") FROM " + DB_QUESTION_TABLE;
        try {
            readableOpen();
            Cursor c = mSqliteDatabase.rawQuery(query, null);
            if (c!=null && c.getCount() != 0 ) {
                if (c.moveToFirst()) {
                        date = c.getString(0);
                }
            }
            if(date==null){
                date="2017-01-12 13:00:16";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
        return date;
    }

    public Question getQuestion(int id)
    {
        Question mQuestion=null;
        try {
            writableOpen();
            Cursor cursor = mSqliteDatabase.query(DB_QUESTION_TABLE, new String[]{DB_QUESTION_TABLE_ID, DB_QUESTION_TABLE_QUESTION, DB_QUESTION_TABLE_HINT, DB_QUESTION_TABLE_SOUND_URL, DB_QUESTION_TABLE_CREATED_AT, DB_QUESTION_TABLE_UPDATED_AT}, DB_QUESTION_TABLE_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor.getCount() != 0) {
                if (cursor != null) {
                    cursor.moveToFirst();
                    mQuestion = new Question(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(4));
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
        return mQuestion;
    }
}
