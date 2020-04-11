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

/**
* FragmentOne class creates a Fragment for the first country question
* using onCreateView method.
*/
public class FragmentSix extends Fragment {
    // elements from layout 
    TextView question;
    RadioButton button1, button2, button3;
    Button moveButton;
    RadioGroup group;
    
    // ArrayLists and String variables for answer choices 
    ArrayList<String> wrongContinents = new ArrayList<String>();
    ArrayList<String> answerChoices = new ArrayList<String>();
    QuizDBHelper db;
    String correctAnswer = "";

    /**
    * FragmentOne is an empty public constructor
    */
    public FragmentSix() {
        // Required empty public constructor
    }

    /**
    * onCreateView is a method that creates the view for the fragment.
    * @param inflater, container, savedInstanceState
    * @return View
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Intialize the view with the layout
        View view = inflater.inflate(R.layout.fragment_six, container, false);
        
        // Find the question placeholder on layout
        question = (TextView) view.findViewById(R.id.section_label);
        
        // Find the RadioButtons on the layout
        button1 = (RadioButton) view.findViewById(R.id.radioButton1);
        button2 = (RadioButton) view.findViewById(R.id.radioButton2);
        button3 = (RadioButton) view.findViewById(R.id.radioButton3);
        
        // Find moveButton on the layout and start Final.class intent if selected 
        moveButton = (Button) view.findViewById(R.id.button5);
        Intent intent = new Intent(getActivity(), Final.class);
        moveButton.setOnClickListener(e -> {
            startActivity(intent);
        });
        
        // Generate 3 answer choices by have 2 incorrect and 1 correct continent 
        // Add continents to wrongContinents
        wrongContinents.add("Asia");
        wrongContinents.add("Africa");
        wrongContinents.add("North America");
        wrongContinents.add("South America");
        wrongContinents.add("Europe");
        wrongContinents.add("Oceania");
        
        // Set countryName as the question
        String countryName = QuizDBHelper.countries.get(5);
        question.setText(countryName);
        // Set countryName to correctAnswer and add it to answerChoices
        correctAnswer = MainActivity.hs.get(countryName);
        answerChoices.add(correctAnswer);
        
        // Get other random incorrect countries and add answerChoices, only 3 answers
        Random randomGenerator = new Random();
        while(answerChoices.size() <3) {
            int random = randomGenerator.nextInt(6);
            if (!answerChoices.contains(wrongContinents.get(random))) {
                answerChoices.add(wrongContinents.get(random));
            }
        }
        Log.d("ANSWER CHOICES", " " +answerChoices.toString());
        
        // Shuffle answerChoices and set the RadioButtons to the answerChoices
        Collections.shuffle(answerChoices);
        button1.setText(answerChoices.get(0));
        button2.setText(answerChoices.get(1));
        button3.setText(answerChoices.get(2));

        // Check if button is selected and updated answers array if correct
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON RESPONSE", "Correct answer: " + correctAnswer + " buttonText: " + button1.getText().toString());
                Log.d("CORRECT", "Are they the same: " + button1.getText().toString().equals(correctAnswer));
                if (button1.getText().toString().equals(correctAnswer)) {
                    MainActivity.answers[5] = 1;
                    Log.d("ENTER" , "Entered correct");
                    Log.d("VALUE", " " + MainActivity.answers[5]);
                } else {
                    MainActivity.answers[5] = 0;
                }
            }
        });
        
        // Check if button is selected and updated answers array if correct
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON RESPONSE", "Correct answer: " + correctAnswer + " buttonText: " + button2.getText().toString());
                Log.d("CORRECT", "Are they the same: " + button2.getText().toString().equals(correctAnswer));
                if (button2.getText().toString().equals(correctAnswer)) {
                    MainActivity.answers[5] = 1;
                    Log.d("ENTER" , "Entered correct");
                    Log.d("VALUE", " " + MainActivity.answers[5]);
                } else {
                    MainActivity.answers[5] = 0;
                }

            }
        });
        
        // Check if button is selected and updated answers array if correct
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON RESPONSE", "Correct answer: " + correctAnswer + " buttonText: " + button3.getText().toString());
                Log.d("CORRECT", "Are they the same: " + button3.getText().toString().equals(correctAnswer));
                if (button3.getText().toString().equals(correctAnswer)) {
                    MainActivity.answers[5] = 1;
                    Log.d("ENTER" , "Entered correct");
                    Log.d("VALUE", " " + MainActivity.answers[5]);
                } else {
                    MainActivity.answers[5] = 0;
                }

            }
        });
        
        // Return view 
        return view;

    }
}
