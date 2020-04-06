package edu.uga.cs.quizapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FragmentOne extends Fragment {

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        readQuizData();
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    //ArrayList for country and continent in CSV
    ArrayList<String> country = new ArrayList<String>();
    ArrayList<String> continent = new ArrayList<String>();

    private void readQuizData() {
        Resources res = getResources();
        InputStream in_continent = res.openRawResource( R.raw.country_continent );
        BufferedReader reader = new BufferedReader(
          new InputStreamReader(in_continent, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");

                country.add(tokens[0]);
                continent.add(tokens[1]);

                System.out.println(country.get(0) + continent.get(0));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
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


}
