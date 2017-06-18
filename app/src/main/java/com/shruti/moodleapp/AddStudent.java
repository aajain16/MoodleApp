package com.shruti.moodleapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddStudent extends AppCompatActivity {
    Activity activity = this;
    Spinner spinner,spinner1;
    EditText fname,lname,pname,contact,pcontact,email,address,year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.divisions);
        spinner.setAdapter(adapter);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.divisions1);
        spinner1.setAdapter(adapter1);
        Button btn = (Button) findViewById(R.id.buttonSAVE);

        fname=(EditText)findViewById(R.id.edit_fname);
        lname=(EditText)findViewById(R.id.edit_lname);
        pname=(EditText)findViewById(R.id.edit_pname);
        contact=(EditText)findViewById(R.id.contact);
        pcontact=(EditText)findViewById(R.id.p_contact);
        email=(EditText)findViewById(R.id.edit_email);
        address=(EditText)findViewById(R.id.edit_address);
        year=(EditText)findViewById(R.id.edit_year);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_student(v);
            }
        });
    }
    public void register_student(View view)
    {
        String branch = spinner.getSelectedItem().toString();
        String sem = spinner1.getSelectedItem().toString();
        String method="register_student";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(
                method,
                fname.getText().toString(),
                lname.getText().toString(),
                pname.getText().toString(),
                contact.getText().toString(),
                pcontact.getText().toString(),
                email.getText().toString(),
                address.getText().toString(),
                year.getText().toString(),
                branch,
                sem);
        finish();
    }
}
