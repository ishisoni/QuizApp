package edu.uga.cs.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * QuizDBHelper is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app.
 */
public class QuizDBHelper extends SQLiteOpenHelper {
    // Variables to set up table for quiz data
    public static final String DB_NAME = "quiz.db";
    public static final String DEBUG_TAG = "QuizLead";
    public static final int DB_VERSION = 5;
    public static QuizDBHelper helperInstance;
    public static ArrayList<String> countries = new ArrayList<String>();
    
    // Tag names for DB quiz-table 
    public static final String TABLE_QUIZ1   = "quiz1";
    public static final String QUIZ_COLUMN_ID1 = "quizId1";
    public static final String QUIZ_COLUMN_DATE1 = "quizDate1";
    public static final String QUIZ_COLUMN_Q11 = "q11";
    public static final String QUIZ_COLUMN_Q21 = "q21";
    public static final String QUIZ_COLUMN_Q31 = "q31";
    public static final String QUIZ_COLUMN_Q41 = "q41";
    public static final String QUIZ_COLUMN_Q51 = "q51";
    public static final String QUIZ_COLUMN_Q61 = "q61";
    public static final String QUIZ_COLUMN_CORRECT1 = "percentageCorrect1";
    // Tag names for DB quiz-table
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
    //Tag names for DB country-table
    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRY_COLUMN_ID = "countryId";//primary key
    public static final String COUNTRY_COLUMN = "country";//country
    public static final String CONTINENT_COLUMN = "continent";//continent
    
    // Columns for quiz table
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
    
    // Columns for country table
    private static final String[] allColumnsCountry = {
            QuizDBHelper.COUNTRY_COLUMN_ID,
            QuizDBHelper.COUNTRY_COLUMN,
            QuizDBHelper.CONTINENT_COLUMN,

    };

    // Create tables query 
    private static final String CREATE_TABLE =
            String.format("create table%s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", TABLE_COUNTRIES, COUNTRY_COLUMN_ID, COUNTRY_COLUMN, CONTINENT_COLUMN);

    // Query to create quiz table
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

    // Query to create country table
    private static final String CREATE_COUNTRIES =
            "create table " + TABLE_COUNTRIES + " ("
                    +COUNTRY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +COUNTRY_COLUMN + " TEXT, "
                    +CONTINENT_COLUMN + " TEXT "
                    +")";

    /**
    * QuizDBHelper constructor that sets up DB with name and version
    * @param context - context of the app
    */
    public QuizDBHelper(Context context) {
        super( context, DB_NAME, null, DB_VERSION );

    }

    /**
    * getInstance is a method where the DB can access the elements 
    * @param context - context of the app
    * @return helperInstance
    */
    public static synchronized QuizDBHelper getInstance(Context context) {
        if (helperInstance == null ) {
            helperInstance = new QuizDBHelper(context.getApplicationContext());

        }
        return helperInstance;
    }

    /**
    * onCreate is a method that executes SQL query to create tables in DB
    * @param db - the database
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_QUIZ);
        db.execSQL(CREATE_COUNTRIES);
    }

    /**
    * onUpgrade is a method that executes SQL query to drop tables in DB 
    * if version number changes, automatically invoked by Android if needed.
    */ 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_QUIZ);
        db.execSQL("drop table if exists " + TABLE_COUNTRIES);
        onCreate(db);
    }

    /**
    * insertCountry is a method that inserts country and contient into DB
    * @param country, continent
    * @return wasAdded
    */
    public long insertCountry(String country, String continent) {
        //retrieved method from QuizData
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( QuizDBHelper.COUNTRY_COLUMN, country);
        values.put( QuizDBHelper.CONTINENT_COLUMN, continent);
    
        // If properly added, return wasAdded
        long wasAdded = db.insert(TABLE_COUNTRIES, null, values);
        return wasAdded;
    }

    /**
    * onCreateCountries is a method that creates the country table
    */ 
    public void onCreateCountries(SQLiteDatabase db) {
        db.execSQL(CREATE_COUNTRIES);

    }
    
    /**
    * dropDB drops country table from the DB and recreates country table
    */
   public void dropDB() {
       SQLiteDatabase db = this.getWritableDatabase();
       db.execSQL("drop table if exists " + TABLE_COUNTRIES);
       onCreateCountries(db);
   }

    /**
    * createQuestions is a method that creates the questions for the QuizApp
    * and creates date to insert into DB
     * @return the date of the quiz
    */
    public String createQuestions() {
        // start DB and and clear previous countries 
        countries.clear();
        SQLiteDatabase db= this.getWritableDatabase();
        
        // set up date for the QuizApp
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dbdate = formatter.format(date);
        System.out.println(formatter.format(date));
        
        // generate 6 random numbers to get countries from DB for the quiz
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < 6) {
            int random = randomGenerator .nextInt(195);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        Log.d("DEBUG", "NUMBER OF RANDOM COUNTRIES:" + numbers.size());
        
        // get these random numbers from DB by querying using cursor and DB
        Cursor cursor = null;
        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
           cursor = db.query( QuizDBHelper.TABLE_COUNTRIES, allColumnsCountry,
               "countryId in (" + numbers.get(0)+ "," + numbers.get(1)+ "," + numbers.get(2)+ "," + numbers.get(3)+ "," + numbers.get(4)+ "," + numbers.get(5)+ ")", null, null, null, null );
            
            // keep getting countries until cursor is finished
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    // get all attribute values of this job lead
                    String countryName = cursor.getString(cursor.getColumnIndex( QuizDBHelper.COUNTRY_COLUMN) );
                    Log.d( "DEBUG", "Country Name: " + countryName );
                    countries.add(countryName);
                    Log.d("DEBUG", "NUMBER OF COUNTRIES TO LIST:" + countries.size());
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
        
        // store countries and date into DB
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

        // if properly added, long wasAdded will not equal 0
        long wasAdded = db.insert(TABLE_QUIZ, null, values);

        return dbdate;
    }
    
    /**
    * insertResults is a method that stores the result from the user's quiz completion 
    * @param finalScore, dbdate
    */
    public void insertResults(int finalScore, String dbdate) {
        // set up DB and execute UPDATE SQL command to store finalScore
        SQLiteDatabase db  = this.getWritableDatabase();
        db.execSQL("UPDATE quiz SET percentageCorrect = '" + finalScore + "' WHERE quizDate = '" + dbdate + "'");
    }

    /**
    * retrieveAllQuizLeads is a method that retrieves all quiz leads and returns it
    * @return quizLeads
    */ 
    public List<QuizLead> retrieveAllQuizLeads() {
        ArrayList<QuizLead> quizLeads = new ArrayList<>();
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( QuizDBHelper.TABLE_QUIZ, allColumnsQuiz,
                    null, null, null, null, null );

            // collect all quiz leads into a List
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    // get all attribute values of this quiz lead
                    String date = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_DATE) );
                    int score = cursor.getInt( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_CORRECT) );


                    // create a new QUizLead object and set its state to the retrieved values
                    QuizLead quizLead = new QuizLead(date, score);

                    quizLeads.add(quizLead);
                    Log.d( DEBUG_TAG, "Retrieved JobLead: " + quizLead );
                }
            }
            Log.d( DEBUG_TAG, "Number of records from DB: " + cursor.getCount() );
        }
        catch( Exception e ){
            Log.d( DEBUG_TAG, "Exception caught: " + e );
        }
        finally{
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        // return a list of retrieved quiz leads
        return quizLeads;
    }
}
