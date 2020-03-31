package edu.uga.cs.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is facilitates storing and restoring job leads stored.
 */
public class QuizData {

    public static final String DEBUG_TAG = "QuizData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase   db;
    private SQLiteOpenHelper quizDbHelper;
    private static final String[] allColumns = {
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

    public QuizData( Context context ) {
        this.quizDbHelper = QuizDBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = quizDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "QuizData: db open" );
    }

    // Close the database
    public void close() {
        if( quizDbHelper != null ) {
            quizDbHelper.close();
            //Log.d(DEBUG_TAG, "JobLeadsData: db closed");
        }
    }

    // Retrieve all job leads and return them as a List.
    // This is how we restore persistent objects stored as rows in the job leads table in the database.
    // For each retrieved row, we create a new JobLead (Java POJO object) instance and add it to the list.
    public List<QuizLead> retrieveAllJobLeads() {
        ArrayList<QuizLead> quizLeads = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query( QuizDBHelper.TABLE_QUIZ, allColumns,
                    null, null, null, null, null );

            // collect all job leads into a List
            if( cursor.getCount() > 0 ) {
                while( cursor.moveToNext() ) {
                    // get all attribute values of this job lead
                    long id = cursor.getLong( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_ID ) );
                    String date = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_DATE ) );
                    String q1 = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_Q1 ) );
                    String q2 = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_Q2 ) );
                    String q3 = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_Q3 ) );
                    String q4 = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_Q4 ) );
                    String q5 = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_Q5 ) );
                    String q6 = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_Q6 ) );
                    String correct = cursor.getString( cursor.getColumnIndex( QuizDBHelper.QUIZ_COLUMN_CORRECT ) );

                    // create a new JobLead object and set its state to the retrieved values
                    QuizLead   quizLead = new QuizLead( date, q1, q2, q3, q4, q5, q6, correct );
                    quizLead.setId( id ); // set the id (the primary key) of this object
                    // add it to the list
                    quizLeads.add( quizLead );

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
        // return a list of retrieved job leads
        return quizLeads;
    }

    // Store a new job lead in the database.
    public QuizLead storeJobLead( QuizLead quizLead ) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the JobLead argument.
        // This is how we are providing persistence to a JobLead (Java object) instance
        // by storing it as a new row in the database table representing job leads.
        ContentValues values = new ContentValues();
        values.put( QuizDBHelper.QUIZ_COLUMN_DATE, quizLead.getDate());
        values.put( QuizDBHelper.QUIZ_COLUMN_Q1, quizLead.getQ1() );
        values.put( QuizDBHelper.QUIZ_COLUMN_Q2, quizLead.getQ2() );
        values.put( QuizDBHelper.QUIZ_COLUMN_Q3, quizLead.getQ3() );
        values.put( QuizDBHelper.QUIZ_COLUMN_Q4, quizLead.getQ4() );
        values.put( QuizDBHelper.QUIZ_COLUMN_Q5, quizLead.getQ5() );
        values.put( QuizDBHelper.QUIZ_COLUMN_Q6, quizLead.getQ6() );
        values.put( QuizDBHelper.QUIZ_COLUMN_CORRECT, quizLead.getCorrect() );
        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        long id = db.insert( QuizDBHelper.TABLE_QUIZ, null, values );

        // store the id (the primary key) in the JobLead instance, as it is now persistent
        quizLead.setId( id );

        Log.d( DEBUG_TAG, "Stored new job lead with id: " + String.valueOf( quizLead.getId() ) );

        return quizLead;
    }


}
