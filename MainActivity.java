package com.example.samanthatran.pullwebcontent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    //Create new thread
    NetworkConnection asyncTask = new NetworkConnection();

    //Create public variables for TextViews so they can be used in all functions
    public TextView movie1;
    public TextView movie2;
    public TextView movie3;
    public TextView times1;
    public TextView times2;
    public TextView times3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //acknowledge movie title and movie time variables
        movie1 = (TextView) findViewById(R.id.movieOne);
        movie2 = (TextView) findViewById(R.id.movieTwo);
        movie3 = (TextView) findViewById(R.id.movieThree);
        times1 = (TextView) findViewById(R.id.timesOne);
        times2 = (TextView) findViewById(R.id.timesTwo);
        times3 = (TextView) findViewById(R.id.timesThree);
        //Make them invisible for now until read in data
        movie1.setVisibility(View.INVISIBLE);
        movie2.setVisibility(View.INVISIBLE);
        movie3.setVisibility(View.INVISIBLE);
        times1.setVisibility(View.INVISIBLE);
        times2.setVisibility(View.INVISIBLE);
        times3.setVisibility(View.INVISIBLE);



        //create delegate to enable response
        asyncTask.delegate = this;

        Button DL_button = (Button) findViewById(R.id.DownloadContent);
        DL_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Append URL to input data
                String stringUrl = "https://www.regencymovies.com/dateDetail.php?theaterId=27&date=";
                String addDataExtension = "2016-05-15";
                stringUrl = stringUrl + addDataExtension;
                //Send URL to new thread to read html data
                asyncTask.execute(stringUrl);
            }
        });
    }

    //override the function from asyncTask
    public void processFinish(String output){
        //do something with output string (HTML code)
        Log.d("DEBUGGING", output);

        //Create pseudo 2D array to hold movie titles and times
        //Allow up to 3 movies and each have up to 9 times
        //Spots [0][0], [1][0], and [2][0] will be movie titles
        //all elements will initally be NULL
        String [][] movieInfo = new String[3][10];
        //initialize all elements to "none"
        for (int i = 0; i <3; i++){
            for (int j = 0; j < 10; j++){
                movieInfo[i][j] = "None";
            }
        }

        //Create pseudo data
        movieInfo[0][0] = "Captain America";
        movieInfo[1][0] = "Star Wars";

        movieInfo[0][1] = "3:15";
        movieInfo[0][2] = "4:30";
        movieInfo[0][3] = "9:00";

        movieInfo[1][1] = "5:00";
        movieInfo[1][2] = "6:00";
        movieInfo[1][3] = "7:00";

        //Set data in array to textView items
        //If movie is "None" then we don't want to display anything
        if(movieInfo[0][0] != "None"){
            movie1.setText(movieInfo[0][0]);
            //Create string of movie times from data
            int i = 1;
            String movieTimes1 = "";
            //Cycle through until we finish seeing the times. If it says None, stop.
            while(movieInfo[0][i] != "None"){
                movieTimes1+=movieInfo[0][i];
                movieTimes1+="  ";
                i++;
            }
            times1.setText(movieTimes1);
            //set visible
            movie1.setVisibility(View.VISIBLE);
            times1.setVisibility(View.VISIBLE);
        }

        if(movieInfo[1][0] != "None"){
            movie2.setText(movieInfo[1][0]);
            //Create string of movie times from data
            int i = 1;
            String movieTimes2 = "";
            //Cycle through until we finish seeing the times. If it says None, stop.
            while(movieInfo[1][i] != "None"){
                movieTimes2+=movieInfo[1][i];
                movieTimes2+="  ";
                i++;
            }
            times2.setText(movieTimes2);
            //set visible
            movie2.setVisibility(View.VISIBLE);
            times2.setVisibility(View.VISIBLE);
        }

        if(movieInfo[2][0] != "None"){
            movie3.setText(movieInfo[2][0]);
            //Create string of movie times from data
            int i = 1;
            String movieTimes3 = "";
            //Cycle through until we finish seeing the times. If it says None, stop.
            while(movieInfo[2][i] != "None"){
                movieTimes3+=movieInfo[2][i];
                movieTimes3+="  ";
                i++;
            }
            times3.setText(movieTimes3);
            //set visible
            movie3.setVisibility(View.VISIBLE);
            times3.setVisibility(View.VISIBLE);
        }


        Log.d("DEBUG","temp");



    }



}
