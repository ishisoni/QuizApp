package edu.uga.cs.quizapp;

import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.net.SocketOption;


/**
 * This class is an activity to create a new job lead.
 */
public class NewQuizLeadActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static int int_items = 7;

    public static final String DEBUG_TAG = "NewQuizLeadActivity";


    private QuizData quizData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz_lead);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

    }

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentOne();
                case 1:
                    return new FragmentTwo();
                case 2:
                    return new FragmentThree();
                case 3:
                    return new FragmentFour();
                case 4:
                    return new FragmentFive();
                case 5:
                    return new FragmentSix();
                case 6:
                    return new FragmentOne(); // Results

            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }
    }

    @Override
    protected void onResume() {
        //Log.d(DEBUG_TAG, "NewJobLeadActivity.onResume()");
        // open the database in onResume
        if (quizData != null)
            quizData.open();

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(DEBUG_TAG, "NewJobLeadActivity.onPause()");
        // close the database in onPause
        if (quizData != null)
            quizData.close();
        super.onPause();
    }
}

