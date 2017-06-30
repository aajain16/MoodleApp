package com.shruti.moodleapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class AppBase extends AppCompatActivity {

    ArrayList<String> basicFields;
    gridAdapter adapter;
    public static ArrayList<String> divisions,divisions1 ;
    GridView gridView;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        basicFields = new ArrayList<>();
        activity = this;
        divisions = new ArrayList();
        divisions.add("IT");
        divisions.add("CE");

        divisions1 = new ArrayList();
        divisions1.add("7");
        divisions1.add("8");
        gridView = (GridView)findViewById(R.id.grid);
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
}
