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
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button quizButton;
    Button resultsButton;
    QuizDBHelper db;
    public static String date;
    public static HashMap<String, String>  hs = new HashMap<String, String>();
    public static int percentage;
    public static int[] answers = {0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new QuizDBHelper(this);
        quizButton = findViewById(R.id.button1);
        Intent intentResults = new Intent(this, Results.class);
        Intent intent = new Intent(this, NewQuizLeadActivity.class);
        quizButton.setOnClickListener(e-> {
            percentage = 0;
            db.dropDB();
            readQuizData();
            date = db.createQuestions();
            startActivity(intent);

        });
        resultsButton = findViewById(R.id.button2);
        resultsButton.setOnClickListener(e-> {
            startActivity(intentResults);

        });

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
                hs.put(tokens[0], tokens[1]);


                //country.add(tokens[0]);
                //continent.add(tokens[1]);

            }


        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
