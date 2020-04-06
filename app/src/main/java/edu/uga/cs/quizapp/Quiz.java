package edu.uga.cs.quizapp;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Quiz {
    private String country;
    private String continent;
    private boolean isCorrect;


    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    public void setContinent(String continent) {
        this.continent = continent;
    }
    public String getContinent() {
        return continent;
    }
    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    public boolean getIsCorrect() {
        return isCorrect;
    }

    public static HashMap<String, String> countryCon = new HashMap<>();
    public static HashSet quizQuestions = new HashSet();
    public static Random random = new Random();

    public static void createQuestions() {
        while(quizQuestions.size()<7) {
            List<String> keys = new ArrayList<String>(countryCon.keySet());
            String randomKey = keys.get(random.nextInt(keys.size()) );
            //value = countryCon.get(randomKey);
            quizQuestions.add(randomKey);
            //question.setText(randomKey);
           // Log.d("Countries"," "+ randomKey);
        }
    }

    public static void main(String[] args) {
        String csvFile = "../QuizApp/app/src/main/res/raw/country_continent.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";


        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                countryCon.put(country[0],country[1]);

                //System.out.println("Country [country " + country[0] + " , continent=" + country[1] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        createQuestions();

    }

    /*private void readQuizData() {
        Resources res = .getResources();
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
    }*/

}

