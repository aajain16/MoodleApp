package com.shruti.moodleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class ClassAdd extends AppCompatActivity{

    private Session session;
    EditText et_subject;
    Spinner spn_branch,spn_sem,spn_admyear;
    FloatingActionButton fab_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_add);

        session= new Session(this);

        et_subject=(EditText)findViewById(R.id.et_subject);
        spn_branch=(Spinner)findViewById(R.id.spn_branch);
        spn_sem=(Spinner)findViewById(R.id.spn_sem);
        spn_admyear=(Spinner)findViewById(R.id.spn_year) ;

        fab_save=(FloatingActionButton)findViewById(R.id.fab_save);
        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String branch = spn_branch.getSelectedItem().toString();
                String semester = spn_sem.getSelectedItem().toString();
                String admyear = spn_admyear.getSelectedItem().toString();
                String method="class_add";
                BackgroundTask backgroundTask=new BackgroundTask(ClassAdd.this);
                backgroundTask.execute(
                        method,
                        session.getid(),
                        et_subject.getText().toString().replaceAll(" ","_"),
                        semester,
                        branch,
                        admyear);
//                finish();
//                Toast.makeText(ClassAdd.this,"subject "+et_subject.getText().toString(),Toast.LENGTH_SHORT).show();
//                Toast.makeText(ClassAdd.this,"branch "+branch,Toast.LENGTH_SHORT).show();
//                Toast.makeText(ClassAdd.this,"semester "+semester,Toast.LENGTH_SHORT).show();
//                Toast.makeText(ClassAdd.this,"admyear "+admyear,Toast.LENGTH_SHORT).show();
//                Toast.makeText(ClassAdd.this,"id "+session.getid(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ClassAdd.this,ClassData.class);
                startActivity(intent);
            }
        });
    }
}