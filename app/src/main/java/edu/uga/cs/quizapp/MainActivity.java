package edu.uga.cs.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
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

/**
* MainActivty class acts as a SplashScreen for the QuizApp
*/
public class MainActivity extends AppCompatActivity {
    // Elements from layout
    Button quizButton;
    Button resultsButton;
    
    // Variables needed to collect and store data
    QuizDBHelper db;
    public static String date;
    public static HashMap<String, String>  hs = new HashMap<String, String>();
    public static int percentage;
    public static int[] answers = {0, 0, 0, 0, 0, 0};

    /**
    * onCreate is a method that creates the view for MainActivity.
    * @param savedInstanceState
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Intializing buttons and DB helper
        db = new QuizDBHelper(this);
        quizButton = findViewById(R.id.button1);
        
        // If user selects quiz button, goes to NewQuizLeadActivity
        Intent intentResults = new Intent(this, Results.class);
        Intent intent = new Intent(this, NewQuizLeadActivity.class);
        quizButton.setOnClickListener(e-> {
            percentage = 0;
            db.dropDB();
            readQuizData();
            date = db.createQuestions();
            startActivity(intent);

        });

        // If user selects results button, goes to Result
        resultsButton = findViewById(R.id.button2);
        resultsButton.setOnClickListener(e-> {
            startActivity(intentResults);

        });

    }
    
    /**
    * readQuizData is a method that reads the CSV file and stores the countries
    * and their respective continents in the DB
    */ 
    private void readQuizData() {
        // Needed classses to access file and read file
        Resources res = getResources();
        InputStream in_continent = res.openRawResource( R.raw.country_continent );
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in_continent, Charset.forName("UTF-8"))
        );

        // Reads the file and trys to store in DB
        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                db.insertCountry(tokens[0],tokens[1]);
                hs.put(tokens[0], tokens[1]);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
