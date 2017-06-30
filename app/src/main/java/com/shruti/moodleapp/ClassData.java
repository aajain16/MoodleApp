package com.shruti.moodleapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ClassData extends AppCompatActivity implements AdapterView.OnItemClickListener {

    FloatingActionButton fab;

    ListView lv_classes;
    ClassAdapter classAdapter;
    ArrayList<String> lssubject = new ArrayList<String>();
    ArrayList<String> lsbranch = new ArrayList<String>();
    ArrayList<String> lssemester = new ArrayList<String>();
    String[] arsubject = {};
    String[] arbranch = {};
    String[] arsemester = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_data);
        fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClassData.this,ClassAdd.class);
                startActivity(intent);
            }
        });
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimaryDark));

        lv_classes = (ListView)findViewById(R.id.lv_classes);
        if(arbranch!=null) {
            classAdapter = new ClassAdapter(ClassData.this, arbranch, arsemester, arsubject);
            lv_classes.setAdapter(classAdapter);
        }
        lv_classes.setOnItemClickListener(this);

    }

    @Override
    public void onStart()
    {
        super.onStart();
        ClassTask ct=new ClassTask();
        ct.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ViewGroup v = (ViewGroup) view;
        TextView tv_sub = (TextView)findViewById(R.id.tv_sub);
        TextView tv_branch = (TextView)findViewById(R.id.tv_branch);
        TextView tv_sem = (TextView)findViewById(R.id.tv_sem);

        Intent intent = new Intent(this,AttendanceSheet.class);
        intent.putExtra("subject",tv_sub.getText().toString());
        intent.putExtra("branch",tv_branch.getText().toString());
        intent.putExtra("semester",tv_sem.getText().toString());
        startActivity(intent);
        finish();
    }

    private class ClassTask extends AsyncTask<Void, Void, Void>
    {
        ArrayList<String> subject;
        ArrayList<String> branch;
        ArrayList<String> semester;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            subject = new ArrayList<String>();
            branch = new ArrayList<String>();
            semester = new ArrayList<String>();

            lssubject.clear();
            lsbranch.clear();
            lssemester.clear();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            String myResponseString = null;
            try
            {
                URL url = new URL("http://192.168.43.167/cms/classdata.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                InputStream IS=httpURLConnection.getInputStream();
                myResponseString = IOUtils.toString(IS);
                IS.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try
            {
                JSONArray jArray =new JSONArray(myResponseString);
//                JSONArray jArray =new JSONArray("[{\"subject\":\"Java\",\"semester\":\"7\",\"branch\":\"IT\"}]");
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    subject.add(jsonObject.getString("subject"));
                    branch.add(jsonObject.getString("branch"));
                    semester.add(jsonObject.getString("semester"));
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            lssubject.addAll(subject);
            lsbranch.addAll(branch);
            lssemester.addAll(semester);

            classAdapter.notifyDataSetChanged();

            arsubject = lssubject.toArray(new String[lssubject.size()]);
            arbranch = lsbranch.toArray(new String[lsbranch.size()]);
            arsemester = lssemester.toArray(new String[lssemester.size()]);

            if(arbranch!=null) {
                classAdapter = new ClassAdapter(ClassData.this, arbranch, arsemester, arsubject);
                lv_classes.setAdapter(classAdapter);
            }
        }
    }

}