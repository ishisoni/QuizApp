package edu.uga.cs.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    public static final String DEBUG_TAG = "QuizLead";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    QuizDBHelper db;
    List<QuizLead> quizResults;

    private static final String[] allColumnsQuiz = {
            QuizDBHelper.QUIZ_COLUMN_DATE,
            QuizDBHelper.QUIZ_COLUMN_CORRECT,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new QuizDBHelper(this);
        setContentView(R.layout.activity_results);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       quizResults =  db.retrieveAllQuizLeads();
       db.retrieveAllQuizLeads();

        // specify an adapter (see also next example)
        mAdapter = new ResultsAdapter(quizResults);
        recyclerView.setAdapter(mAdapter);


    }


}
