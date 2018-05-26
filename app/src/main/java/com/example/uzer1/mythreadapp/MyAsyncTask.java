package com.example.uzer1.mythreadapp;

import android.os.AsyncTask;

/**
 * Created by uzer1 on 23/05/2018.
 */

public class MyAsyncTask extends AsyncTask {

    @Override
    protected  void onPreExecute()
    {
        setContentView(R.layout.activity_second);

    }
    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    protected void onPostExecute(Object o)
    {

    }
}
