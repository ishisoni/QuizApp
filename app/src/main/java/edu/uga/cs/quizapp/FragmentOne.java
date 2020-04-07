package edu.uga.cs.quizapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.HashMap;
import java.util.HashSet;

import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FragmentOne extends Fragment {
    TextView question;

    RadioButton button1, button2, button3;

    RadioGroup group;

    public FragmentOne() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //readQuizData();
        
       View view = inflater.inflate(R.layout.fragment_one, container, false);
        question = (TextView) view.findViewById(R.id.section_label);




       // Iterator<String> i = quizQuestions.iterator();
       // while (i.hasNext()) {
            //System.out.println(i.next());
         //   String next = i.next();
          //  Log.d("Countries", next);
       // }

        /*
        //quizQuestions

        Quiz quizQuestion = new Quiz();
        //quizQuestion.setCountry(country.get(randomNumber));
        //quizQuestion.setContinent(continent.get(randomNumber));
        button1 = (RadioButton) view.findViewById(R.id.radioButton1);
        button2 = (RadioButton) view.findViewById(R.id.radioButton2);
        button3 = (RadioButton) view.findViewById(R.id.radioButton3);
        int randomRadio = random.nextInt(2) + 1;
        wrongContinents.add("Asia");
        wrongContinents.add("Africa");
        wrongContinents.add("North America");
        wrongContinents.add("South America");
        wrongContinents.add("Europe");
        wrongContinents.add("Oceania");
        switch(randomRadio) {
            case 1: {
               // button1.setText(continent.get(randomNumber));
                button2.setText("Wrong answer");
                button3.setText("Wrong asnwer");
                break;
            }
            case 2: {
                button1.setText("Wrong Answer");
               // button2.setText(continent.get(randomNumber));
                button3.setText("Wrong answer");
                break;
            }
            case 3: {
                button1.setText("Wrong Answer");
                button2.setText("Wrong Answer");
              //  button3.setText(continent.get(randomNumber));
                break;
            }

        }

         */

        return view;

    }

    //ArrayList for country and continent in CSV


   /* private void readQuizData() {
        Resources res = getResources();
        InputStream in_continent = res.openRawResource( R.raw.country_continent );
        BufferedReader reader = new BufferedReader(
          new InputStreamReader(in_continent, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                countryCon.put(tokens[0], tokens[1]);

                //country.add(tokens[0]);
                //continent.add(tokens[1]);

                //Log.d("Array contents", country.get(0)+continent.get(0));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    */



    //Do CSV Reading

    //read each line (while loop) and put country and continent in separate arrays
    // do smth like String[] stuff = line.split(",");
    //line comes from the while loop
    //store stuff[0] in list called country
    //store stuff[1] in list called continent


    //Do random question & answers

    // using the lists, get 1 country and it's correct continent with 2 random ones
    // store these in new 2 strings and a array list (countryQuestion, continentCorrect & continentsAnswers)


    //how will we change the buttons with every swipe -- might  need to make separate fragments
    //Set up Radio Buttons

    //pull section_label by TextView question = (TextView) findViewById(R.id.section_label)
    //edit question accordingly using string and array list
    //pull radioButtons with their respective id's and setText to the answers--randomly

    //Store the response from the RadioGroup in an array
    // how do we make an array accessible accross fragments--is that a thing
    //---stop here please for god's sakes---
    //after this, work on checking answers and producing results

    //create a Quiz class with three private variables- question(country), response(from radio button), and boolean(isCorrect)



}
