package com.shruti.moodleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Faculty extends AppCompatActivity {
    TextView tv_attendence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        tv_attendence=(TextView)findViewById(R.id.tv_attendence);
        tv_attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faculty.this,FacultyAttendenceSubDetail.class);
                startActivity(intent);
            }
        });

    }
}
