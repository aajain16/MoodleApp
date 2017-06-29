package com.shruti.moodleapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class TestQuiz extends AppCompatActivity {

    ListView lv_quiz;
    Button btn_test_submit;
    TestQuizAdapter testQuizAdapter;
    List<String> answers = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_quiz);

        Intent i = getIntent();
        String[] que = i.getStringArrayListExtra("lsquestions").toArray(new String[]{});
        String[] opta = i.getStringArrayListExtra("lsa").toArray(new String[]{});
        String[] optb = i.getStringArrayListExtra("lsb").toArray(new String[]{});
        String[] optc = i.getStringArrayListExtra("lsc").toArray(new String[]{});
        String[] optd = i.getStringArrayListExtra("lsd").toArray(new String[]{});
        final String[] ans = i.getStringArrayListExtra("lsanswers").toArray(new String[]{});

        lv_quiz = (ListView)findViewById(R.id.lv_quiz);
        btn_test_submit = (Button)findViewById(R.id.btn_test_submit);

        if(que!=null)
        {
            testQuizAdapter = new TestQuizAdapter(this,que,opta,optb,optc,optd);
            lv_quiz.setAdapter(testQuizAdapter);
        }

        btn_test_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemscount = lv_quiz.getChildCount();
                answers.clear();
                for (int i = 0;i < itemscount;i++)
                {
                    View v = lv_quiz.getChildAt(i);
                    Spinner spn = (Spinner)v.findViewById(R.id.spn_test_ans);
                    String temp = (String) spn.getSelectedItem();
                    answers.add(temp);
                }
                String[] uans = answers.toArray(new String[]{});
                getmarks(ans,uans);
            }
        });
    }

    void getmarks(String[] tans, String[] uans)
    {
        int total = 0;
        if(tans.length==uans.length)
        {
            for (int i = 0;i < tans.length;i++)
            {
                if(tans[i].equals(uans[i]))
                    total++;
            }
        }
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Total Marks")
                .setMessage("Marks Earned : "+total)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
