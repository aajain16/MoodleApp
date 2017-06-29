package com.shruti.moodleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.shruti.moodleapp.Quiz_edit.quizname;

public class Questions_edit extends AppCompatActivity {

    EditText et_edit_question,et_edit_A,et_edit_B,et_edit_C,et_edit_D;
    Spinner spn_edit_ans;
    Button btn_editque;

    String cond_que;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_edit);

        et_edit_question = (EditText)findViewById(R.id.et_edit_question);
        et_edit_A = (EditText)findViewById(R.id.et_edit_A);
        et_edit_B = (EditText)findViewById(R.id.et_edit_B);
        et_edit_C = (EditText)findViewById(R.id.et_edit_C);
        et_edit_D = (EditText)findViewById(R.id.et_edit_D);
        spn_edit_ans = (Spinner)findViewById(R.id.spn_edit_ans);
        btn_editque = (Button)findViewById(R.id.btn_editque);

        Intent i = getIntent();
        et_edit_question.setText(i.getStringExtra("que"));
        et_edit_A.setText(i.getStringExtra("A"));
        et_edit_B.setText(i.getStringExtra("B"));
        et_edit_C.setText(i.getStringExtra("C"));
        et_edit_D.setText(i.getStringExtra("D"));

        cond_que = et_edit_question.getText().toString().trim();

        btn_editque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String method="question_edit";
                BackgroundTask backgroundTask=new BackgroundTask(getApplicationContext());
                backgroundTask.execute(
                        method,
                        et_edit_question.getText().toString(),
                        et_edit_A.getText().toString(),
                        et_edit_B.getText().toString(),
                        et_edit_C.getText().toString(),
                        et_edit_D.getText().toString(),
                        spn_edit_ans.getSelectedItem().toString(),
                        quizname,
                        cond_que);
                finish();
            }
        });
    }
}
