package com.shruti.moodleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.shruti.moodleapp.Quiz_edit.quizname;

public class Questions_add extends AppCompatActivity {

    EditText et_add_question,et_add_A,et_add_B,et_add_C,et_add_D;
    Spinner spn_add_ans;
    Button btn_addque;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_add);
//
//        Intent i = getIntent();
//        final String quizname = i.getStringExtra("quizname");

        et_add_question = (EditText) findViewById(R.id.et_add_question);
        et_add_A = (EditText) findViewById(R.id.et_add_A);
        et_add_B = (EditText) findViewById(R.id.et_add_B);
        et_add_C = (EditText) findViewById(R.id.et_add_C);
        et_add_D = (EditText) findViewById(R.id.et_add_D);
        spn_add_ans = (Spinner) findViewById(R.id.spn_add_ans);
        btn_addque = (Button) findViewById(R.id.btn_addque);

        btn_addque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String method="question_add";
                BackgroundTask backgroundTask=new BackgroundTask(getApplicationContext());
                backgroundTask.execute(
                        method,
                        et_add_question.getText().toString(),
                        et_add_A.getText().toString(),
                        et_add_B.getText().toString(),
                        et_add_C.getText().toString(),
                        et_add_D.getText().toString(),
                        spn_add_ans.getSelectedItem().toString(),
                        quizname);
                finish();
            }
        });
    }
}
