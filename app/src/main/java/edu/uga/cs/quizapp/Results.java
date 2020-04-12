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

/**
 *
 * Class for displaying the results of all fo the quizzes previously taken
 */
public class Results extends AppCompatActivity {

    public static final String DEBUG_TAG = "QuizLead";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    QuizDBHelper db;
    List<QuizLead> quizResults;
    //set up variables


    /**
     *
     * onCreate method to create results of prior quizzes
     * @param savedInstanceState - current state of app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize db
        db = new QuizDBHelper(this);
        setContentView(R.layout.activity_results);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       quizResults =  db.retrieveAllQuizLeads();
       //retrieve results from the quiz
       db.retrieveAllQuizLeads();

        // specify an adapter (see also next example)
        mAdapter = new ResultsAdapter(quizResults);
        //set up adapter
        recyclerView.setAdapter(mAdapter);
        //apply adapter to recycler view


    }


}
