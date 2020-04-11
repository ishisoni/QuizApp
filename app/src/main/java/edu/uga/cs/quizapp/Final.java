package edu.uga.cs.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Final extends AppCompatActivity {

    TextView score;
    Button mainButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        QuizDBHelper db = new QuizDBHelper(this);

        score = (TextView) findViewById(R.id.score);

        Log.d("SCORE", "1: " + MainActivity.answers[0] + "2: " + MainActivity.answers[1] +"3: " + MainActivity.answers[2] +"4: " + MainActivity.answers[3] +"5: " + MainActivity.answers[4] +"6: " + MainActivity.answers[5]);
        int finalScore = MainActivity.answers[0] + MainActivity.answers[1] +MainActivity.answers[2] +MainActivity.answers[3] +MainActivity.answers[4] + MainActivity.answers[5];
        score.setText(finalScore + "/6");
        db.insertResults(finalScore, MainActivity.date);

        Intent intent = new Intent(this, MainActivity.class);
        mainButton = (Button) findViewById(R.id.button);
        mainButton.setOnClickListener(e-> {
            startActivity(intent);

        });
    }
}
