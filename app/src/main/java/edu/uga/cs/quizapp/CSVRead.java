package edu.uga.cs.quizapp;

import android.os.Bundle;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;



/** A simple app to illustrate reading from a CSV file and
 * constructing a TableLayout programmatically.
 *
 * Please note that the dependencies in the build.gradle (Module: app) file must
 * include a directive
 *     implementation 'com.opencsv:opencsv:4.2'
 * to provide the necessary CSV library.
 */
public class CSVRead extends AppCompatActivity {

    final String TAG = "CSVReading";

    private Button rankingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*rankingButton = (Button) findViewById( R.id.button );*/
        rankingButton.setOnClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                /*TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main);*/
                Resources res = getResources();
                InputStream in_continent = res.openRawResource( R.raw.country_continent );


                // set up margins for each TextView in the table layout


                // read the CSV data
                CSVReader readerContinent = new CSVReader( new InputStreamReader( in_continent ) );
                //CSVReader readerNeighbor = new CSVReader( new InputStreamReader( in_neighbors ) );
                String [] nextLine;

                //continent reader


            } catch (Exception e) {
                Log.e( TAG, e.toString() );
            }
        }
    }
}