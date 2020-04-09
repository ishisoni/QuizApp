package edu.uga.cs.quizapp;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FragmentFinal extends Fragment {

    TextView score;
    Button mainButton;

    public FragmentFinal() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //readQuizData();

        View view = inflater.inflate(R.layout.fragment_final, container, false);

        QuizDBHelper db = new QuizDBHelper(getActivity());
        db.insertResults(MainActivity.percentage, MainActivity.date);
        score = (TextView) view.findViewById(R.id.score);
        double finalScore = MainActivity.percentage;
        finalScore = Math.ceil((finalScore / 6)*100);
        score.setText(finalScore + " %");

        Intent intent = new Intent(getActivity(), MainActivity.class);
        mainButton = (Button) view.findViewById(R.id.button);
        mainButton.setOnClickListener(e-> {
            startActivity(intent);

        });

        return view;

    }

}





