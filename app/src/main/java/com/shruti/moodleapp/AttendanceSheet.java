package com.shruti.moodleapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AttendanceSheet extends AppCompatActivity {

    ListView lv_atnsheet;
    AtndnceSheetAdapter atndnceSheetAdapter;
    ArrayList<String> lsatnData = new ArrayList<String>();

    Button submit_atndnce;

    String subject,branch,semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_sheet);

        Intent i = getIntent();
        subject = i.getStringExtra("subject");
        branch = i.getStringExtra("branch");
        semester = i.getStringExtra("semester");

        lv_atnsheet = (ListView)findViewById(R.id.lv_atnsheet);
        if(lsatnData!=null) {
            atndnceSheetAdapter = new AtndnceSheetAdapter(AttendanceSheet.this,lsatnData.toArray(new String[lsatnData.size()]));
            lv_atnsheet.setAdapter(atndnceSheetAdapter);
        }

        submit_atndnce = (Button)findViewById(R.id.submit_atndnce);
        submit_atndnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AttendanceSheet.this,"Attendance Uploaded Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AttendanceSheet.this,ClassData.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        AttendanceTask at=new AttendanceTask();
        at.execute();
    }

    private class AttendanceTask extends AsyncTask<Void, Void, Void>
    {
        ArrayList<String> data;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            data = new ArrayList<String>();
            lsatnData.clear();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            String myResponseString = null;
            try
            {
                URL url = new URL("http://192.168.43.167/cms/atendancedata.php");
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8")+"&"+
                        URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+
                        URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(semester,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                myResponseString = IOUtils.toString(IS);
                IS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try
            {
                JSONArray jArray =new JSONArray(myResponseString);
//                JSONArray jArray =new JSONArray("[{\"s_id\":\"2016IT1\",\"s_fname\":\"JAYESH\",\"s_lname\":\"KORI\"},{\"s_id\":\"2016IT2\",\"s_fname\":\"AKSHAT\",\"s_lname\":\"JAIN\"}]");
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    data.add(jsonObject.getString("s_id")+"  "+jsonObject.getString("s_fname")+" "+jsonObject.getString("s_lname"));
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

            lsatnData.addAll(data);
            atndnceSheetAdapter.notifyDataSetChanged();

            if(lsatnData!=null) {
                atndnceSheetAdapter = new AtndnceSheetAdapter(AttendanceSheet.this,lsatnData.toArray(new String[]{}));
                lv_atnsheet.setAdapter(atndnceSheetAdapter);
            }
        }
    }
}