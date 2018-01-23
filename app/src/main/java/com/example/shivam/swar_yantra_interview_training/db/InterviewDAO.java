package com.example.shivam.swar_yantra_interview_training.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shivam.swar_yantra_interview_training.data.Interview;
import com.example.shivam.swar_yantra_interview_training.utility.DBHelper;

import java.util.ArrayList;

import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_TABLE;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_TABLE_DATE;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_TABLE_ID;
import static com.example.shivam.swar_yantra_interview_training.utility.DBHelper.DB_INTERVIEW_TABLE_NAME;
/**
 * Created by Shivam on 1/5/2018.
 */


//Used for insertion and deletion in the Session table;
public class InterviewDAO {
    private Context mContext;
    private SQLiteDatabase mSqliteDatabase;
    private DBHelper mDBHandler;

    public InterviewDAO(Context mContext)
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

    public void addInterview(Interview mInterview)
    {
        try {
            writableOpen();
            ContentValues c = new ContentValues();
            c.put(DB_INTERVIEW_TABLE_DATE,mInterview.getInterviewDate() );
            c.put(DB_INTERVIEW_TABLE_NAME,mInterview.getInterviewName());
            mSqliteDatabase.insert(DB_INTERVIEW_TABLE, null, c);
            Log.i("Data", "Data Inserted into Interview Table...");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
    }

    public ArrayList<Interview> getAllInterviews(){
        ArrayList<Interview> allInterviewList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_INTERVIEW_TABLE;
        try {
            readableOpen();
            Cursor c = mSqliteDatabase.rawQuery(query, null);
            if(c!=null && c.getCount()>0) {
                if (c.moveToFirst()) {
                    do {
                        Integer id = c.getInt(0);
                        String interviewName = c.getString(1);
                        String interviewDate = c.getString(2);
                        allInterviewList.add(new Interview(id,interviewName,interviewDate));
                    } while (c.moveToNext());
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            mSqliteDatabase.close();
        }
        return allInterviewList;
    }

    public int getInterviewId(String interviewDate){
        String query = "SELECT "+DB_INTERVIEW_TABLE_ID+" FROM " + DB_INTERVIEW_TABLE+" WHERE "+DB_INTERVIEW_TABLE_DATE+"='"+interviewDate+"'";
        int id=0;
        try {
            writableOpen();
            Cursor c = mSqliteDatabase.rawQuery(query, null);
            if(c!=null && c.getCount()>0) {
                if (c.moveToFirst()) {
                    id = c.getInt(0);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            mSqliteDatabase.close();
        }
        return id;
    }

    public Interview getInterview(int id)
    {
        try {
            writableOpen();
            Cursor cursor = mSqliteDatabase.query(DB_INTERVIEW_TABLE, new String[]{DB_INTERVIEW_TABLE_ID, DB_INTERVIEW_TABLE_NAME, DB_INTERVIEW_TABLE_DATE}, DB_INTERVIEW_TABLE_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                Interview mInterViewSession = new Interview(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2));
                return mInterViewSession;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            mSqliteDatabase.close();
        }
        return null;
    }
}
