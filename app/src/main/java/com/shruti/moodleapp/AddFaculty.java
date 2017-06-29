package com.shruti.moodleapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddFaculty extends AppCompatActivity {
    Calendar c = Calendar.getInstance();
    TextView jngdt,dob;
    int cday, cmonth, cyear;
    Spinner spn_branch;
    Button buttonADD;
    EditText et_fname,et_lname,et_contact,et_email,et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        spn_branch = (Spinner) findViewById(R.id.spn_branch);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AppBase.divisions);
        spn_branch.setAdapter(adapter);

        et_fname = (EditText)findViewById(R.id.edit_fname);
        et_lname = (EditText)findViewById(R.id.edit_lname);
        et_contact = (EditText)findViewById(R.id.edit_contact);
        et_email = (EditText)findViewById(R.id.edit_email);
        et_address = (EditText)findViewById(R.id.edit_address);

        buttonADD = (Button)findViewById(R.id.buttonADD);
        buttonADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    register_faculty(v);
            }
        });

        /* Date picker code */

        dob=(TextView)findViewById(R.id.tv_dob);
        jngdt = (TextView) findViewById(R.id.tv_joindate);
        jngdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddFaculty.this, d,
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       new DatePickerDialog(AddFaculty.this, db,
                                           c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                                           .get(Calendar.DAY_OF_MONTH)).show();

                                   }
                               });
        /*goto datepickerr part2*/
    }

    private void register_faculty(View v) {
        String branch = spn_branch.getSelectedItem().toString();
        String method="register_faculty";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(
                method,
                et_fname.getText().toString(),
                et_lname.getText().toString(),
                et_contact.getText().toString(),
                et_email.getText().toString(),
                et_address.getText().toString(),
                dob.getText().toString(),
                branch,
                jngdt.getText().toString());
        finish();
    }

    /*date picker part 2*/
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            cday = dayOfMonth;
            cmonth = monthOfYear + 1;
            cyear = year;
            jngdt.setText(cday + "/" + cmonth + "/" + cyear);
        }
    };

    DatePickerDialog.OnDateSetListener db = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cday = dayOfMonth;
            cmonth = monthOfYear + 1;
            cyear = year;
            dob.setText(cday + "/" + cmonth + "/" + cyear);
        }
    };
    /*Ending of date picker*/
}
