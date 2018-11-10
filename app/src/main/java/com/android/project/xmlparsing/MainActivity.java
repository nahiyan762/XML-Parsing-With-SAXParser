package com.android.project.xmlparsing;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        MyTask task=new MyTask();
        task.setProgressBar(progressBar);
        task.execute();

    }

    class MyTask extends AsyncTask
    {

        ProgressBar progressBar;
        private XMLHelper xmlHelper;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            xmlHelper = new XMLHelper();
            xmlHelper.get();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            ArrayList<PostValue> temp_list=new ArrayList<>();

            for(PostValue postValue:xmlHelper.postValueArrayList){
                temp_list.add(postValue);
                }

            MyAdapter myAdapter  = new MyAdapter(temp_list,getApplicationContext() );
            setListAdapter(myAdapter);
        }

        public void setProgressBar(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }
    }

    private void setListAdapter(MyAdapter myAdapter) {
        progressBar.setVisibility(View.GONE);
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(myAdapter);
    }

    public void goAway(View view){
        Intent intent = new Intent(getApplicationContext(), GoAwayActivity.class);
        startActivity(intent);
    }

}
