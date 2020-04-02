package edu.uga.cs.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button quizButton;
    Button resultsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizButton = findViewById(R.id.button1);
        Intent intent = new Intent(this, NewQuizLeadActivity.class);
        quizButton.setOnClickListener(e-> {
            startActivity(intent);
        });
        resultsButton = findViewById(R.id.button2);

    }
}
