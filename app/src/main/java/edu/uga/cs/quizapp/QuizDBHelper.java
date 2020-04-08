package edu.uga.cs.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
    public static final int DB_VERSION = 2;
    public static QuizDBHelper helperInstance;
    public static ArrayList<String> countries = new ArrayList<String>();
    public static int percentage;

    public static final String TABLE_QUIZ = "quiz";
    public static final String QUIZ_COLUMN_ID = "quizId";
    public static final String QUIZ_COLUMN_DATE = "quizDate";
    public static final String QUIZ_COLUMN_Q1 = "q1";
    public static final String QUIZ_COLUMN_Q2 = "q2";
    public static final String QUIZ_COLUMN_Q3 = "q3";
    public static final String QUIZ_COLUMN_Q4 = "q4";
    public static final String QUIZ_COLUMN_Q5 = "q5";
    public static final String QUIZ_COLUMN_Q6 = "q6";
    public static final String QUIZ_COLUMN_CORRECT = "percentageCorrect";

    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRY_COLUMN_ID = "countryId";//primary key
    public static final String COUNTRY_COLUMN = "country";//country
    public static final String CONTINENT_COLUMN = "continent";//continent

    private static final String[] allColumnsQuiz = {
            QuizDBHelper.QUIZ_COLUMN_ID,
            QuizDBHelper.QUIZ_COLUMN_DATE,
            QuizDBHelper.QUIZ_COLUMN_Q1,
            QuizDBHelper.QUIZ_COLUMN_Q2,
            QuizDBHelper.QUIZ_COLUMN_Q3,
            QuizDBHelper.QUIZ_COLUMN_Q4,
            QuizDBHelper.QUIZ_COLUMN_Q5,
            QuizDBHelper.QUIZ_COLUMN_Q6,
            QuizDBHelper.QUIZ_COLUMN_CORRECT,

    };
    private static final String[] allColumnsCountry = {
            QuizDBHelper.COUNTRY_COLUMN_ID,
            QuizDBHelper.COUNTRY_COLUMN,
            QuizDBHelper.CONTINENT_COLUMN,

    };


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
                    + QUIZ_COLUMN_CORRECT + " INTEGER "
                    + ")";


    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
                    +COUNTRY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +COUNTRY_COLUMN + " TEXT, "
                    +CONTINENT_COLUMN + " TEXT "
                    +")";


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
        db.execSQL(CREATE_COUNTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_QUIZ);
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        onCreate(db);
    }

    public long insertCountry(String country, String continent) {
        //retrieved method from QuizData
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( QuizDBHelper.COUNTRY_COLUMN, country);
        values.put( QuizDBHelper.CONTINENT_COLUMN, continent);

        long wasAdded = db.insert(TABLE_COUNTRIES, null, values);
        return wasAdded;
    }



   public void dropDB() {
       SQLiteDatabase db = this.getWritableDatabase();
       db.execSQL("drop table if exists " + TABLE_QUIZ);
       db.execSQL("drop table if exists " + TABLE_COUNTRIES);
       onCreate(db);
   }

    public String createQuestions() {
        SQLiteDatabase db= this.getWritableDatabase();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dbdate = formatter.format(date);
        System.out.println(formatter.format(date));

        ArrayList<Integer> numbers = new ArrayList<Integer>();

        Random randomGenerator = new Random();
        while (numbers.size() < 7) {
            int random = randomGenerator .nextInt(195);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        Cursor cursor = null;
        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
           cursor = db.query( QuizDBHelper.TABLE_COUNTRIES, allColumnsCountry,
               "countryId in (" + numbers.get(0)+ "," + numbers.get(1)+ "," + numbers.get(2)+ "," + numbers.get(3)+ "," + numbers.get(4)+ "," + numbers.get(5)+ ")", null, null, null, null );
            // collect all job leads into a List

            //cursor = db.query( QuizDBHelper.TABLE_COUNTRIES, allColumnsCountry,
               //     "COUNTRY_COLUMN_ID in (" + number, null, null, null, null );
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    // get all attribute values of this job lead
                    String countryName = cursor.getString(cursor.getColumnIndex( QuizDBHelper.COUNTRY_COLUMN) );
                    Log.d( "DEBUG", "Country Name: " + countryName );
                    countries.add(countryName);
                }
            }
            Log.d( "DEBUG", "Number of records from DB: " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( "DEBUG", "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }

        ContentValues values = new ContentValues();
        //DATE TIME
        values.put( QuizDBHelper.QUIZ_COLUMN_DATE, dbdate);
        values.put( QuizDBHelper.QUIZ_COLUMN_Q1, countries.get(0));
        values.put( QuizDBHelper.QUIZ_COLUMN_Q2, countries.get(1));
        values.put( QuizDBHelper.QUIZ_COLUMN_Q3, countries.get(2));
        values.put( QuizDBHelper.QUIZ_COLUMN_Q4, countries.get(3));
        values.put( QuizDBHelper.QUIZ_COLUMN_Q5, countries.get(4));
        values.put( QuizDBHelper.QUIZ_COLUMN_Q6, countries.get(5));
        values.put( QuizDBHelper.QUIZ_COLUMN_CORRECT, 0);

        long wasAdded = db.insert(TABLE_QUIZ, null, values);

        return dbdate;



    }

    /*public String retrieveQuestion(int questionNumber, String dbdate) {
        SQLiteDatabase db= this.getWritableDatabase();
        String countryName = "";
        Cursor cursor = null;
        Cursor cursor1 = null;
        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( QuizDBHelper.TABLE_QUIZ, allColumnsQuiz,
                    " quizDate = " + dbdate , null, null, null, null );
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    // get all attribute values of this job lead
                    countryName = cursor.getString(cursor.getColumnIndex("q"+ questionNumber) );
                    Log.d( "DEBUG", "Country Name For Quiz: " + countryName );
                }
            }
            Log.d( "DEBUG", "Number of records from DB: " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( "DEBUG", "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return countryName;
    }*/
}
