package edu.uga.cs.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
* Final class creates an activity to show users their result from the quiz 
* which is called on when they select the "View Results" button
*/
public class Final extends AppCompatActivity {
    // Elements from layout
    TextView score;
    Button mainButton;

    /**
    * onCreate creates the activty and displays user's results from the quiz
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        // Access DB helper class to store score
        QuizDBHelper db = new QuizDBHelper(this);
        
        // Find score placeholder on layout
        score = (TextView) findViewById(R.id.score);
        
        // Get finalScore by adding up results and add to DB
        Log.d("SCORE", "1: " + MainActivity.answers[0] + "2: " + MainActivity.answers[1] +"3: " + MainActivity.answers[2] +"4: " + MainActivity.answers[3] +"5: " + MainActivity.answers[4] +"6: " + MainActivity.answers[5]);
        int finalScore = MainActivity.answers[0] + MainActivity.answers[1] +MainActivity.answers[2] +MainActivity.answers[3] +MainActivity.answers[4] + MainActivity.answers[5];
        score.setText(finalScore + "/6");
        db.insertResults(finalScore, MainActivity.date);

        // Returns user home when they click mainButton
        Intent intent = new Intent(this, MainActivity.class);
        mainButton = (Button) findViewById(R.id.button);
        mainButton.setOnClickListener(e-> {
            startActivity(intent);

        });
    }
}
