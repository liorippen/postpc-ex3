package com.example.uzer1.mythreadapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.AsyncTask);
        Button b2 = (Button) findViewById(R.id.ThreadsActivity);
        final Activity mainActivity = this;


        b1.setOnClickListener(new View.OnClickListener(){

                                  @Override
                                  public void onClick(View v) {
                                      MyAsyncTask myAsyncTask = new MyAsyncTask();
                                      myAsyncTask.execute(mainActivity);

                                  }
                              }
        );

//        b2.setOnClickListener(new View.OnClickListener(){
//
//                                  @Override
//                                  public void onClick(View v) {
//                                      MyThread myThread = new MyThread();
//                                      myThread.start();
//
//                                  }
//                              }
//        );


    }
}
