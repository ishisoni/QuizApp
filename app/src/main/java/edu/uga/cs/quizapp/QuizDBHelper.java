package edu.uga.cs.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app.
 *
 *
 * Only one instance of this class will exist.  To make sure, the
 * only constructor is private.
 * Access to the only instance is via the getInstance method.
 */
public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "quiz.db";
    private static final int DB_VERSION = 1;
    private static QuizDBHelper helperInstance;

    public static final String TABLE_QUIZ = "quiz";
    public static final String QUIZ_COLUMN_ID = "quizId";
    public static final String QUIZ_COLUMN_DATE = "quizDate";
    public static final String QUIZ_COLUMN_Q1 = "q1";
    public static final String QUIZ_COLUMN_Q2 = "q2";
    public static final String QUIZ_COLUMN_Q3 = "q3";
    public static final String QUIZ_COLUMN_Q4 = "q4";
    public static final String QUIZ_COLUMN_Q5 = "q5";
    public static final String QUIZ_COLUMN_Q6 = "q6";
    public static final String QUIZ_COLUMN_CORRECT = "correctAnswer";

    private static final String CREATE_QUIZ =
            "create table " + TABLE_QUIZ + " ("
                    + QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZ_COLUMN_DATE  + " TEXT, "
                    + QUIZ_COLUMN_Q1 + " TEXT, "
                    + QUIZ_COLUMN_Q2 + " TEXT, "
                    + QUIZ_COLUMN_Q3 + " TEXT, "
                    + QUIZ_COLUMN_Q4 + " TEXT, "
                    + QUIZ_COLUMN_Q5 + " TEXT, "
                    + QUIZ_COLUMN_Q6 + " TEXT, "
                    + QUIZ_COLUMN_CORRECT + " TEXT "
                    + ")";
    private QuizDBHelper(Context context) {
        super( context, DB_NAME, null, DB_VERSION );

    }

    public static synchronized QuizDBHelper getInstance(Context context) {
        if (helperInstance == null ) {
            helperInstance = new QuizDBHelper(context.getApplicationContext());

        }
        return helperInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_QUIZ);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_QUIZ);
        onCreate(db);
    }


}
