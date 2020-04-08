package edu.uga.cs.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    Button quizButton;
    Button resultsButton;
    QuizDBHelper db;

    //moved to database class, NOT needed here
    /*private static final String[] quizColumns = {
            QuizDBHelper.QUIZ_COLUMN_ID,
            QuizDBHelper.QUIZ_COLUMN_DATE,
            QuizDBHelper.QUIZ_COLUMN_Q1,
            QuizDBHelper.QUIZ_COLUMN_Q2,
            QuizDBHelper.QUIZ_COLUMN_Q3,
            QuizDBHelper.QUIZ_COLUMN_Q4,
            QuizDBHelper.QUIZ_COLUMN_Q5,
            QuizDBHelper.QUIZ_COLUMN_Q6,
            QuizDBHelper.QUIZ_COLUMN_CORRECT,
            QuizDBHelper.QUIZ_COLUMN_A1,
            QuizDBHelper.QUIZ_COLUMN_A2,
            QuizDBHelper.QUIZ_COLUMN_A3,
            QuizDBHelper.QUIZ_COLUMN_A4,
            QuizDBHelper.QUIZ_COLUMN_A5,
            QuizDBHelper.QUIZ_COLUMN_A6,
            QuizDBHelper.QUIZ_COLUMN_C1,
            QuizDBHelper.QUIZ_COLUMN_C2,
            QuizDBHelper.QUIZ_COLUMN_C3,
            QuizDBHelper.QUIZ_COLUMN_C4,
            QuizDBHelper.QUIZ_COLUMN_C5,
            QuizDBHelper.QUIZ_COLUMN_C6,

    };
    private static final String[] countryColumns = {
            QuizDBHelper.COUNTRY_COLUMN_ID,
            QuizDBHelper.COUNTRY_COLUMN,
            QuizDBHelper.CONTINENT_COLUMN,

    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new QuizDBHelper(this);
        quizButton = findViewById(R.id.button1);
        Intent intent = new Intent(this, NewQuizLeadActivity.class);
        quizButton.setOnClickListener(e-> {
            readQuizData();
            startActivity(intent);

        });
        resultsButton = findViewById(R.id.button2);

    }


    // Open the database


    private void readQuizData() {
        Resources res = getResources();
        InputStream in_continent = res.openRawResource( R.raw.country_continent );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in_continent, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                //this has been moved to QuizDBhelper!
               // ContentValues values = new ContentValues();
               // values.put( QuizDBHelper.COUNTRY_COLUMN, tokens[0]);
               // values.put( QuizDBHelper.CONTINENT_COLUMN, tokens[1]);
                db.insertCountry(tokens[0],tokens[1]);


                //country.add(tokens[0]);
                //continent.add(tokens[1]);

            }


        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
