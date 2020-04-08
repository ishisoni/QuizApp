package edu.uga.cs.quizapp;

import android.content.ContentValues;
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
    public static final String DB_NAME = "quiz.db";
    public static final int DB_VERSION = 1;
    public static QuizDBHelper helperInstance;

    public static final String TABLE_QUIZ = "quiz";
    public static final String QUIZ_COLUMN_ID = "quizId";
    public static final String QUIZ_COLUMN_DATE = "quizDate";
    public static final String QUIZ_COLUMN_Q1 = "q1";
    public static final String QUIZ_COLUMN_Q2 = "q2";
    public static final String QUIZ_COLUMN_Q3 = "q3";
    public static final String QUIZ_COLUMN_Q4 = "q4";
    public static final String QUIZ_COLUMN_Q5 = "q5";
    public static final String QUIZ_COLUMN_Q6 = "q6";
    public static final String QUIZ_COLUMN_A1 = "a1";//user answers?
    public static final String QUIZ_COLUMN_A2 = "a2";
    public static final String QUIZ_COLUMN_A3 = "a3";
    public static final String QUIZ_COLUMN_A4 = "a4";
    public static final String QUIZ_COLUMN_A5 = "a5";
    public static final String QUIZ_COLUMN_A6 = "a6";
    public static final String QUIZ_COLUMN_C1 = "c1";//correct answers
    public static final String QUIZ_COLUMN_C2 = "c2";
    public static final String QUIZ_COLUMN_C3 = "c3";
    public static final String QUIZ_COLUMN_C4 = "c4";
    public static final String QUIZ_COLUMN_C5 = "c5";
    public static final String QUIZ_COLUMN_C6 = "c6";
    public static final String QUIZ_COLUMN_CORRECT = "percentageCorrect";

    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRY_COLUMN_ID = "id";//primary key
    public static final String COUNTRY_COLUMN = "country";//country
    public static final String CONTINENT_COLUMN = "continent";//continent

    private static final String CREATE_TABLE =
            String.format("create table%s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", TABLE_COUNTRIES, COUNTRY_COLUMN_ID, COUNTRY_COLUMN, CONTINENT_COLUMN);



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
                    + QUIZ_COLUMN_A1 + " TEXT, "
                    + QUIZ_COLUMN_A2 + " TEXT, "
                    + QUIZ_COLUMN_A3 + " TEXT, "
                    + QUIZ_COLUMN_A4 + " TEXT, "
                    + QUIZ_COLUMN_A5 + " TEXT, "
                    + QUIZ_COLUMN_A6 + " TEXT, "
                    + QUIZ_COLUMN_C1 + " TEXT, "
                    + QUIZ_COLUMN_C2 + " TEXT, "
                    + QUIZ_COLUMN_C3 + " TEXT, "
                    + QUIZ_COLUMN_C4 + " TEXT, "
                    + QUIZ_COLUMN_C5 + " TEXT, "
                    + QUIZ_COLUMN_C6 + " TEXT, "
                    + QUIZ_COLUMN_CORRECT + " INTEGER "
                    + ")";
    public QuizDBHelper(Context context) {
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
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_QUIZ);
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        onCreate(db);
    }

    public long insertCountry(String country, String capital) {
        //retrieved method from QuizData
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( QuizDBHelper.COUNTRY_COLUMN, country);
        values.put( QuizDBHelper.CONTINENT_COLUMN, capital);

        long wasAdded = db.insert(TABLE_COUNTRIES, null, values);
        return wasAdded;
    }

    public long insertQuiz() {
        //TODO method stub
        //QUIZZES TABLE
        //quizzes table will only have the following columns: quizID, date, q1-6, and correct # of answers
        //call create Questions, and return the ID of the row that gets created so we know which row to refer to
        long sample = 0;
        //insert the rows into the database like above

        return sample;
    }

    public void createQuestions() {
        //TODO method stub
        //we need to query the database, select 6 countries and apply them to the quizzes table :) this method will be called in another class


    }
}
