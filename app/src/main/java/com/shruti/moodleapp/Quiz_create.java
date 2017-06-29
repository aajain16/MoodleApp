package com.shruti.moodleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Quiz_create extends AppCompatActivity implements View.OnClickListener {

    EditText et_quizname,et_subject;
    Spinner spn_branch,spn_sem;
    Button btn_cancel,btn_create;

    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_create);

        et_quizname = (EditText) findViewById(R.id.et_quizname);
        et_subject = (EditText) findViewById(R.id.et_subject);
        spn_branch = (Spinner) findViewById(R.id.spn_branch);
        spn_sem = (Spinner) findViewById(R.id.spn_sem);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_create = (Button)findViewById(R.id.btn_create);

        btn_cancel.setOnClickListener(this);
        btn_create.setOnClickListener(this);

        session = new Session(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_cancel)
        {
            Intent i = new Intent(getApplicationContext(),Faculty.class);
            startActivity(i);
        }
        if (view == btn_create)
        {
            String method="quiz_create";
            BackgroundTask backgroundTask=new BackgroundTask((View.OnClickListener) this);
            backgroundTask.execute(
                    method,
                    et_quizname.getText().toString(),
                    session.getid(),
                    spn_branch.getSelectedItem().toString(),
                    spn_sem.getSelectedItem().toString(),
                    et_subject.getText().toString(),
                    "0");
            finish();
//            Toast.makeText(this,session.getfacultyid(),Toast.LENGTH_LONG).show();
        }
    }
}
