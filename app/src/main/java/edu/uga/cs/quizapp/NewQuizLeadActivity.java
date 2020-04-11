package edu.uga.cs.quizapp;

import android.os.AsyncTask;
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
 * NewQuizLeadActivity class handles the fragments used in QuizApp
 */
public class NewQuizLeadActivity extends AppCompatActivity {
    public static final String DEBUG_TAG = "NewQuizLeadActivity";
    
    // Elements from layout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    
    // QuizData elements intialized to null
    private QuizData quizLeadsData = null;
    private QuizData quizData = null;

    // Variables needed to use the Fragments
    public static int int_items = 6;    

    /**
    * onCreate is a method that creates and sets up layout for the Fragments
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz_lead);
        
        // ViewPage elemennt found on layout and set Adapter 
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }
    
    /**
    * MyAdapter estends FragmentPagerAdapter which manages the six Fragments
    * and displays correct Fragment when user swipes
    */
    private class MyAdapter extends FragmentPagerAdapter {
        /**
        * MyAdapter constructor that intializes the FragmentManager
        */
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * getItem is a method that returns correct Fragment with respect to Position
         * @param position
         * @return Fragment
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
            }
            return null;
        }

        /**
        * getCount is a method that returns the number of items/fragments
        * @return int_items
        */
        @Override
        public int getCount() {
            return int_items;
        }
    }

    /**
    * onResume is a method that opens DB along with onResume android functionality 
    */
    @Override
    protected void onResume() {
        // open the database in onResume
        if (quizData != null)
            quizData.open();

        super.onResume();
    }

    /**
    * onPause is a method that uses android OnPause functionality and closes DB while paused
    */
    @Override
    protected void onPause() {
        Log.d(DEBUG_TAG, "NewJobLeadActivity.onPause()");
        // close the database in onPause
        if (quizData != null)
            quizData.close();
        super.onPause();
    }


    /**
    * QuizLeadDBWriterTask extends the AsyncTask with QuizLead as the parameter
    * and conducts DB writing in the background. 
    */
    private class QuizLeadDBWriterTask extends AsyncTask<QuizLead, Void, QuizLead> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected QuizLead doInBackground( QuizLead... quizLead ) {
            quizLeadsData.storeQuizLead( quizLead[0] );
            return quizLead[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( QuizLead quizLead ) {
            super.onPostExecute( quizLead );
            
            Log.d( DEBUG_TAG, "Quiz lead saved: " + quizLead );
        }
    }
}

