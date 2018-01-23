package com.example.shivam.swar_yantra_interview_training.utility;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shivam on 1/5/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String LOG="DBHelper";
    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "SwarYantra";

    public static final String DB_QUESTION_TABLE = "questionTable";
    public static final String DB_QUESTION_TABLE_ID = "questionId";
    public static final String DB_QUESTION_TABLE_QUESTION = "question";
    public static final String DB_QUESTION_TABLE_HINT = "questionHint";
    public static final String DB_QUESTION_TABLE_SOUND_URL = "questionSoundUrl";
    public static final String DB_QUESTION_TABLE_CREATED_AT = "questionCreatedAt";
    public static final String DB_QUESTION_TABLE_UPDATED_AT = "questionUpdatedAt";

    public static final String DB_INTERVIEW_TABLE = "interview";
    public static final String DB_INTERVIEW_TABLE_ID = "interviewId";
    public static final String DB_INTERVIEW_TABLE_NAME = "interviewName";
    public static final String DB_INTERVIEW_TABLE_DATE = "interviewDate";

    public static final String DB_INTERVIEW_RESPONSE_TABLE = "interviewResponse";
    public static final String DB_INTERVIEW_RESPONSE_TABLE_ID = "interviewResponseId";
    public static final String DB_INTERVIEW_RESPONSE_TABLE_INTERVIEW_ID = "interviewId";
    public static final String DB_INTERVIEW_RESPONSE_TABLE_QUESTION_ID = "questionId";
    public static final String DB_INTERVIEW_RESPONSE_TABLE_SOUND_URL = "userSoundUrl";

    private static final String CREATE_QUESTION_TABLE = "CREATE TABLE " + DB_QUESTION_TABLE + " (" + DB_QUESTION_TABLE_ID + " INTEGER PRIMARY KEY," + DB_QUESTION_TABLE_QUESTION + " TEXT," + DB_QUESTION_TABLE_HINT + " TEXT," + DB_QUESTION_TABLE_SOUND_URL + " TEXT," + DB_QUESTION_TABLE_CREATED_AT + " TEXT," + DB_QUESTION_TABLE_UPDATED_AT + " TEXT);";
    private static final String CREATE_INTERVIEW_TABLE = "CREATE TABLE " + DB_INTERVIEW_TABLE + " (" + DB_INTERVIEW_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DB_INTERVIEW_TABLE_NAME + " TEXT DEFAULT NULL," + DB_INTERVIEW_TABLE_DATE + " TEXT);";
    private static final String CREATE_INTERVIEW_RESPONSE_TABLE = "CREATE TABLE " + DB_INTERVIEW_RESPONSE_TABLE + " (" + DB_INTERVIEW_RESPONSE_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DB_INTERVIEW_RESPONSE_TABLE_INTERVIEW_ID + " INTEGER," + DB_INTERVIEW_RESPONSE_TABLE_QUESTION_ID + " INTEGER," + DB_INTERVIEW_RESPONSE_TABLE_SOUND_URL + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
            sqLiteDatabase.execSQL(CREATE_QUESTION_TABLE);
            sqLiteDatabase.execSQL(CREATE_INTERVIEW_TABLE);
            sqLiteDatabase.execSQL(CREATE_INTERVIEW_RESPONSE_TABLE);
            Log.i(LOG,"Database table created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
