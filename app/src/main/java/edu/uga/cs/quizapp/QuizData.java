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
            QuizDBHelper.QUIZ_COLUMN_ID1,
            QuizDBHelper.QUIZ_COLUMN_DATE1,
            QuizDBHelper.QUIZ_COLUMN_Q11,
            QuizDBHelper.QUIZ_COLUMN_Q21,
            QuizDBHelper.QUIZ_COLUMN_Q31,
            QuizDBHelper.QUIZ_COLUMN_Q41,
            QuizDBHelper.QUIZ_COLUMN_Q51,
            QuizDBHelper.QUIZ_COLUMN_Q61,
            QuizDBHelper.QUIZ_COLUMN_CORRECT1,

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
    public List<QuizLead> retrieveAllQuizLeads() {
        ArrayList<QuizLead> quizLeads = new ArrayList<>();
        Cursor cursor = null;
        boolean isTrue = false;
        if (isTrue) {

            try {

                // Execute the select query and get the Cursor to iterate over the retrieved rows
                cursor = db.query(QuizDBHelper.TABLE_QUIZ1, allColumns,
                        null, null, null, null, null);

                // collect all job leads into a List
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        // get all attribute values of this job lead
                        long id = cursor.getLong(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_ID1));
                        String date = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_DATE1));
                        String q1 = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_Q11));
                        String q2 = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_Q21));
                        String q3 = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_Q31));
                        String q4 = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_Q41));
                        String q5 = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_Q51));
                        String q6 = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_Q61));
                        String correct = cursor.getString(cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_CORRECT1));

                        // create a new JobLead object and set its state to the retrieved values
                        // QuizLead   quizLead = new QuizLead( date, q1, q2, q3, q4, q5, q6, correct );
                        //quizLead.setId( id ); // set the id (the primary key) of this object
                        // add it to the list
                        //quizLeads.add( quizLead );

                    }
                }
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());
            } catch (Exception e) {
                Log.d(DEBUG_TAG, "Exception caught: " + e);
            } finally {
                // we should close the cursor
                if (cursor != null) {
                    cursor.close();
                }
            }

        }
        // return a list of retrieved job leads
        return quizLeads;
    }

    // Store a new job lead in the database.
    //make method in here to insert
    public QuizLead storeQuizLead( QuizLead quizLead ) {
            boolean isTrue = false;
        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the JobLead argument.
        // This is how we are providing persistence to a JobLead (Java object) instance
        // by storing it as a new row in the database table representing job leads.
        if (isTrue) {
            ContentValues values = new ContentValues();
            values.put(QuizDBHelper.QUIZ_COLUMN_DATE1, quizLead.getDate());
            values.put(QuizDBHelper.QUIZ_COLUMN_Q11, quizLead.getDate());
            values.put(QuizDBHelper.QUIZ_COLUMN_Q21, quizLead.getDate());
            values.put(QuizDBHelper.QUIZ_COLUMN_Q31, quizLead.getDate());
            values.put(QuizDBHelper.QUIZ_COLUMN_Q41, quizLead.getDate());
            values.put(QuizDBHelper.QUIZ_COLUMN_Q51, quizLead.getDate());
            values.put(QuizDBHelper.QUIZ_COLUMN_Q61, quizLead.getDate());
            values.put(QuizDBHelper.QUIZ_COLUMN_CORRECT1, quizLead.getCorrect());
            long id = db.insert( QuizDBHelper.TABLE_QUIZ1, null, values );

        }
        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.


        // store the id (the primary key) in the JobLead instance, as it is now persistent

        Log.d( DEBUG_TAG, "Stored new job lead with id: ");

        return quizLead;
    }


}
