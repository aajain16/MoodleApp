package com.shruti.moodleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Faculty extends AppCompatActivity implements View.OnClickListener{
    private Session session;


    Button attendence_take,attendence_edit,quiz_new,quiz_edit,quiz_run,quiz_upload,material_upload,material_remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        session=new Session(this);


        attendence_take = (Button)findViewById(R.id.attendence_take);
        attendence_edit = (Button)findViewById(R.id.attendence_edit);
        quiz_new = (Button)findViewById(R.id.quiz_new);
        quiz_edit = (Button)findViewById(R.id.quiz_edit);
        quiz_run = (Button)findViewById(R.id.quiz_run);
        quiz_upload = (Button)findViewById(R.id.quiz_upload);
        material_upload = (Button)findViewById(R.id.material_upload);
        material_remove = (Button)findViewById(R.id.material_remove);

        quiz_new.setOnClickListener(this);
        quiz_edit.setOnClickListener(this);

        attendence_take.setOnClickListener(this);

        material_upload.setOnClickListener(this);

        session.setid("IT1");
    }

    @Override
    public void onClick(View view) {
        if (view == attendence_take)
        {
            Intent intent=new Intent(getApplicationContext(),ClassData.class);
            startActivity(intent);
        }
        if(view == quiz_new)
        {
//            create_quiz();
            Intent i = new Intent(getApplicationContext(),Quiz_create.class);
            startActivity(i);
        }
        if(view == quiz_edit)
        {
            Intent i = new Intent(getApplicationContext(),Quiz_edit.class);
            startActivity(i);
        }

        if (view==material_upload)

        {
            Intent intent=new Intent(Faculty.this,MaterialUpload.class);
            startActivity(intent);
        }
    }

//    private void create_quiz() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Enter Quiz Name");
//
//        final EditText input = new EditText(this);
//        input.setInputType(InputType.TYPE_CLASS_TEXT);
//        builder.setView(input);
//
//        builder.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        builder.show();
//    }
}
