package com.example.samanthatran.pullwebcontent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button DL_button = (Button) findViewById(R.id.DownloadContent);
        DL_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String stringUrl = "http://www.google.com/";
                new NetworkConnection().execute(stringUrl);
            }
        });

        }
}
