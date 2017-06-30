package com.shruti.moodleapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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

public class Quiz_edit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static String quizname;

    Button btn_addmore,btn_refresh,btn_test;
    Spinner spn_quizlist;
    ArrayList<String> quizlist=new ArrayList<>();
    ArrayAdapter<String> quizadapter;

    ListView lView;
    QueAdapter adapter;
    ArrayList<String> lsquestions=new ArrayList<String>();
    ArrayList<String> lsa=new ArrayList<String>();
    ArrayList<String> lsb=new ArrayList<String>();
    ArrayList<String> lsc=new ArrayList<String>();
    ArrayList<String> lsd=new ArrayList<String>();
    ArrayList<String> lsanswers=new ArrayList<String>();
    String[] arquestions = {};
    String[] ara = {};
    String[] arb = {};
    String[] arc = {};
    String[] ard = {};
    String[] aranswers = {};

//    int position;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_edit);

        spn_quizlist = (Spinner) findViewById(R.id.spn_quizlist);
        spn_quizlist.setOnItemSelectedListener(this);
        quizadapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,quizlist);
        spn_quizlist.setAdapter(quizadapter);

        lView = (ListView)findViewById(R.id.lv_qlist);
        if(arquestions!=null)
        {
            adapter = new QueAdapter(this,arquestions,ara,arb,arc,ard);
            lView.setAdapter(adapter);
        }



        btn_addmore = (Button)findViewById(R.id.btn_addmore);
        btn_addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(),Questions_add.class);
                startActivity(i);
            }
        });

        btn_refresh = (Button)findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListTask lt=new ListTask();
                lt.execute();
            }
        });

        btn_test = (Button)findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(),TestQuiz.class);
                i.putExtra("lsd",lsd);
                i.putStringArrayListExtra("lsquestions",lsquestions);
                i.putStringArrayListExtra("lsa",lsa);
                i.putStringArrayListExtra("lsb",lsb);
                i.putStringArrayListExtra("lsc",lsc);
                i.putStringArrayListExtra("lsd",lsd);
                i.putStringArrayListExtra("lsanswers",lsanswers);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        SpinTask st=new SpinTask();
        st.execute();
    }

    private class SpinTask extends AsyncTask<Void,Void,Void>
    {
        ArrayList<String> list;
        protected void onPreExecute()
        {
            super.onPreExecute();
            list=new ArrayList<>();
            quizlist.clear();
        }
        protected Void doInBackground(Void...params)
        {
            String myResponseString = null;
            try
            {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.43.167/cms/quiznames.php");
                HttpResponse httpResponse = httpClient.execute(httpPost);
                myResponseString = EntityUtils.toString(httpResponse.getEntity());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

//          parse json data
            try
            {
//              JSONArray jArray =new JSONArray("[{\"quizname\":\"introduction_of_software\"},{\"quizname\":\"internet_programming\"}]");
                JSONArray jArray =new JSONArray(myResponseString);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    list.add(jsonObject.getString("quizname"));
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            quizlist.addAll(list);
            quizadapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        quizname = spn_quizlist.getSelectedItem().toString();
        ListTask lt=new ListTask();
        lt.execute();
    }

    private class ListTask extends AsyncTask<Void,Void,Void>
    {
        ArrayList<String> que;
        ArrayList<String> a;
        ArrayList<String> b;
        ArrayList<String> c;
        ArrayList<String> d;
        ArrayList<String> ans;

        protected void onPreExecute()
        {
            super.onPreExecute();
            que=new ArrayList<>();
            a=new ArrayList<>();
            b=new ArrayList<>();
            c=new ArrayList<>();
            d=new ArrayList<>();
            ans=new ArrayList<>();

            lsquestions.clear();
            lsa.clear();
            lsb.clear();
            lsc.clear();
            lsd.clear();
            lsanswers.clear();
        }
        protected Void doInBackground(Void...params)
        {
            String myResponseString = null;
            try
            {
                URL url=new URL("http://192.168.43.167/cms/questions.php");
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("quizname","UTF-8")+"="+URLEncoder.encode(quizname,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                myResponseString = IOUtils.toString(IS);
                IS.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
//
//          parse json data
            try
            {
//              JSONArray jArray =new JSONArray("[{\"question\":\"question 1\",\"a\":\"A\",\"b\":\"B\",\"c\":\"C\",\"d\":\"D\"}," +
//                                              "{\"question\":\"question 2 \",\"a\":\"A\",\"b\":\"B\",\"c\":\"C\",\"d\":\"D\"}," +
//                                              "{\"question\":\"question 3\",\"a\":\"A\",\"b\":\"B\",\"c\":\"C\",\"d\":\"D\"}," +
//                                              "{\"question\":\"question 4\",\"a\":\"A\",\"b\":\"B\",\"c\":\"C\",\"d\":\"D\"}]");
                JSONArray jArray =new JSONArray(myResponseString);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    que.add(jsonObject.getString("question"));
                    a.add(jsonObject.getString("a"));
                    b.add(jsonObject.getString("b"));
                    c.add(jsonObject.getString("c"));
                    d.add(jsonObject.getString("d"));
                    ans.add(jsonObject.getString("ans"));
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            lsquestions.addAll(que);
            lsa.addAll(a);
            lsb.addAll(b);
            lsc.addAll(c);
            lsd.addAll(d);
            lsanswers.addAll(ans);
            adapter.notifyDataSetChanged();

            arquestions = lsquestions.toArray(new String[lsquestions.size()]);
            ara = lsa.toArray(new String[lsa.size()]);
            arb = lsb.toArray(new String[lsb.size()]);
            arc = lsc.toArray(new String[lsc.size()]);
            ard = lsd.toArray(new String[lsd.size()]);
            aranswers = lsanswers.toArray(new String[lsanswers.size()]);

            if(arquestions!=null)
            {
                adapter = new QueAdapter(Quiz_edit.this,arquestions,ara,arb,arc,ard);
                lView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


