package com.example.uzer1.mythreadapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView result;
    static Button cr, s, ca;
    MyAsyncTask myAsyncTask;
    static  boolean flag = false;
    static Handler mHandler;
    static Thread t;
    static int count =1;
    Thread[] threads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.AsyncTask);
        Button b2 = (Button) findViewById(R.id.ThreadsActivity);

        b1.setOnClickListener(new View.OnClickListener() {

                                  @Override
                                  public void onClick(View v) {
                                      setContentView(R.layout.activity_second);
                                      result = (TextView) findViewById(R.id.textV);
                                      cr = (Button) findViewById(R.id.create);
                                      s = (Button) findViewById(R.id.start);
                                      ca = (Button) findViewById(R.id.cancel);
                                      cr.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                   myAsyncTask = new MyAsyncTask(result);
                                                                }
                                                            });

                                      s.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              myAsyncTask.execute();
                                          }
                                      });

                                      ca.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              flag =  true;
                                          }
                                      });


                                  }
                              }
        );

        b2.setOnClickListener(new View.OnClickListener() {

                                  @Override
                                  public void onClick(View v) {
                                      setContentView(R.layout.activity_second);
                                      result = (TextView) findViewById(R.id.textV);
                                      cr = (Button) findViewById(R.id.create);
                                      s = (Button) findViewById(R.id.start);
                                      ca = (Button) findViewById(R.id.cancel);
                                      cr.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              mHandler = new Handler();
                                                  t = new Thread(new Runnable() {
                                                      @Override
                                                      public void run() {
                                                              mHandler.post(new Runnable() {
                                                                  @Override
                                                                  public void run() {
                                                                      if (!flag) {
                                                                          result.setText(Integer.toString(count));
                                                                          SystemClock.sleep(500);
                                                                      }

                                                                  }
                                                              });


                                                      }
                                                  });



                                          }
                                      });

                                      s.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              t.start();
                                              try {
                                                  t.join();
                                              } catch (InterruptedException e) {
                                                  e.printStackTrace();
                                              }
                                              if (!flag)
                                              {
                                                  result.setText("Done!");
                                              }


                                          }
                                      });

                                      ca.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              flag =  true;
                                          }
                                      });


                                  }
                              }
        );



    }

        private static class MyAsyncTask extends AsyncTask<Object,String,Object>{

            int count;
            TextView my_result;

            MyAsyncTask(TextView result)

            {
                this.my_result = result;
            }


            @Override
            protected Object doInBackground(Object... params) {
                for (int i = 1; i<= 10; i++)
                {
                    publishProgress(Integer.toString(i));
                    SystemClock.sleep(500);
                    if (flag){
                        break;
                    }
                }
                return null;
            }

            protected void onProgressUpdate(String... progress) {
                super.onProgressUpdate(progress);
                my_result.setText(progress[0]);
            }

            @Override
            protected void onPostExecute(Object o)
            {
                if (!flag) {
                    my_result.setText("Done!");
                }
            }
        }




}
