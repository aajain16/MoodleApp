package com.shruti.moodleapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

public class AppBase extends AppCompatActivity {

    ArrayList<String> basicFields;
    gridAdapter adapter;
    public static ArrayList<String> divisions,divisions1 ;
    GridView gridView;
    public static Activity activity;
//    public static databaseHandler handler;

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu, menu);
        return true;
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        basicFields = new ArrayList<>();
        activity = this;
//        handler = new databaseHandler(this);
//        getSupportActionBar().show();
        divisions = new ArrayList();
        divisions.add("IT");
        divisions.add("CE");

        divisions1 = new ArrayList();
        divisions1.add("7");
        divisions1.add("8");
//        divisions.add("S3 COMPUTER SCIENCE");
//        divisions.add("S4 COMPUTER SCIENCE");
//        divisions.add("S5 COMPUTER SCIENCE");
//        divisions.add("S6 COMPUTER SCIENCE");
//        divisions.add("S7 COMPUTER SCIENCE");
        gridView = (GridView)findViewById(R.id.grid);
//        basicFields.add("ATTENDANCE");
//        basicFields.add("SCHEDULER");
//        basicFields.add("NOTES");
//        basicFields.add("PROFILE");
//        basicFields.add("CGPA CALCULATOR");
        basicFields.add("IMPORT FILE");
        basicFields.add("VIEW");
        basicFields.add("ADD STUDENT");
        basicFields.add("ADD FACULTY");
        basicFields.add("MODIFY STUDENT DETAIL");
        basicFields.add("MODIFY FACULTY DETAIL");
        basicFields.add("REMOVE  STUDENT");
        basicFields.add("REMOVE  FACULTY");

        adapter = new gridAdapter(this,basicFields);
        gridView.setAdapter(adapter);
    }

    public void loadSettings(MenuItem item) {
//        Intent launchIntent = new Intent(this,SettingsActivity.class);
//        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
//        Intent launchIntent = new Intent(this,About.class);
//        startActivity(launchIntent);
    }
}
