package com.shruti.moodleapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class FacultyAddSubject extends AppCompatActivity {
    public String[] arraySpiner;
    EditText et_subject,et_depatment;
    FloatingActionButton fab_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_add_subject);


        et_subject=(EditText)findViewById(R.id.et_subject);
        et_depatment=(EditText)findViewById(R.id.et_department);
        fab_save=(FloatingActionButton)findViewById(R.id.fab_save);
        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FacultyAddSubject.this,FacultyAttendenceSubDetail.class);
                startActivity(intent);
            }
        });


        this.arraySpiner=new String[]{"1st","2nd","3rd","4th"};
        Spinner s=(Spinner)findViewById(R.id.spn_year);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arraySpiner);
        s.setAdapter(adapter);
    }
}
