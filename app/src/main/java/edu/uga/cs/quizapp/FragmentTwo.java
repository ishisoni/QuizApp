package edu.uga.cs.quizapp;

import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FragmentTwo extends Fragment {
    TextView question;

    RadioButton button1, button2, button3;

    RadioGroup group;
    ArrayList<String> wrongContinents = new ArrayList<String>();
    ArrayList<String> answerChoices = new ArrayList<String>();

    public FragmentTwo() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //readQuizData();

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        question = (TextView) view.findViewById(R.id.section_label);


        //quizQuestions


        //quizQuestion.setCountry(country.get(randomNumber));
        //quizQuestion.setContinent(continent.get(randomNumber));
        button1 = (RadioButton) view.findViewById(R.id.radioButton1);
        button2 = (RadioButton) view.findViewById(R.id.radioButton2);
        button3 = (RadioButton) view.findViewById(R.id.radioButton3);

        wrongContinents.add("Asia");
        wrongContinents.add("Africa");
        wrongContinents.add("North America");
        wrongContinents.add("South America");
        wrongContinents.add("Europe");
        wrongContinents.add("Oceania");
        String countryName = QuizDBHelper.countries.get(1);
        question.setText(countryName);
        answerChoices.add(MainActivity.hs.get(countryName));
        Random randomGenerator = new Random();
        while(answerChoices.size() <=3) {
            int random = randomGenerator.nextInt(6);
            if (!answerChoices.contains(wrongContinents.get(random))) {
                answerChoices.add(wrongContinents.get(random));
            }
        }
        Collections.shuffle(answerChoices);
        button1.setText(answerChoices.get(0));
        button2.setText(answerChoices.get(1));
        button3.setText(answerChoices.get(2));


        return view;

    }

}




