package com.shruti.moodleapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by OM on 6/6/2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context context;
    BackgroundTask(Context context)
    {
        this.context=context;
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="";
        String method=params[0];
        if (method.equals("register_student"))
        {
            reg_url="http://192.168.43.167/cms/register_student.php";

            String fname = params[1];
            String lname = params[2];
            String pname = params[3];
            String contact = params[4];
            String pcontact = params[5];
            String email = params[6];
            String address = params[7];
            String year = params[8];
            String branch = params[9];
            String sem = params[10];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("s_fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+
                        URLEncoder.encode("s_lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                        URLEncoder.encode("p_name","UTF-8")+"="+URLEncoder.encode(pname,"UTF-8")+"&"+
                        URLEncoder.encode("s_contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"+
                        URLEncoder.encode("p_contact","UTF-8")+"="+URLEncoder.encode(pcontact,"UTF-8")+"&"+
                        URLEncoder.encode("s_email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("s_address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("s_adm_yr","UTF-8")+"="+URLEncoder.encode(year,"UTF-8")+"&"+
                        URLEncoder.encode("s_branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+
                        URLEncoder.encode("s_sem","UTF-8")+"="+URLEncoder.encode(sem,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Register succesfully";


            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("register_faculty"))
        {
            reg_url="http://192.168.43.167/cms/register_faculty.php";

            String fname = params[1];
            String lname = params[2];
            String contact = params[3];
            String email = params[4];
            String address = params[5];
            String dob=params[6];
            String branch = params[7];
            String jngdt=params[8];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("f_fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+
                        URLEncoder.encode("f_lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                        URLEncoder.encode("f_contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"+
                        URLEncoder.encode("f_email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("f_address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("f_dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8")+"&"+
                        URLEncoder.encode("f_branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+
                        URLEncoder.encode("f_jng_dt","UTF-8")+"="+URLEncoder.encode(jngdt,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Register succesfully";


            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("class_add"))
        {
            reg_url="http://192.168.43.167/cms/class_add.php";

            String facultyid = params[1];
            String subject = params[2];
            String semester = params[3];
            String branch = params[4];
            String admyear = params[5];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("facultyid","UTF-8")+"="+URLEncoder.encode(facultyid,"UTF-8")+"&"+
                        URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8")+"&"+
                        URLEncoder.encode("semester","UTF-8")+"="+URLEncoder.encode(semester,"UTF-8")+"&"+
                        URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+
                        URLEncoder.encode("admyear","UTF-8")+"="+URLEncoder.encode(admyear,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Class Added succesfully";


            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("quiz_create"))
        {
            reg_url="http://192.168.43.167/cms/quiz_create.php";

            String quizname = params[1].replace(" ","_");
            String facultyid =params[2];
            String branch = params[3];
            String sem = params[4];
            String sub = params[5];
            String status = params[6];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("quizname","UTF-8")+"="+URLEncoder.encode(quizname,"UTF-8")+"&"+
                        URLEncoder.encode("facultyid","UTF-8")+"="+URLEncoder.encode(facultyid,"UTF-8")+"&"+
                        URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+
                        URLEncoder.encode("sem","UTF-8")+"="+URLEncoder.encode(sem,"UTF-8")+"&"+
                        URLEncoder.encode("sub","UTF-8")+"="+URLEncoder.encode(sub,"UTF-8")+"&"+
                        URLEncoder.encode("status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Quiz Created Succesfully";


            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("question_add"))
        {
            reg_url="http://192.168.43.167/cms/question_add.php";

            String question = params[1];
            String a =params[2];
            String b = params[3];
            String c = params[4];
            String d = params[5];
            String ans = params[6];
            String quizname = params[7];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("question","UTF-8")+"="+URLEncoder.encode(question,"UTF-8")+"&"+
                        URLEncoder.encode("a","UTF-8")+"="+URLEncoder.encode(a,"UTF-8")+"&"+
                        URLEncoder.encode("b","UTF-8")+"="+URLEncoder.encode(b,"UTF-8")+"&"+
                        URLEncoder.encode("c","UTF-8")+"="+URLEncoder.encode(c,"UTF-8")+"&"+
                        URLEncoder.encode("d","UTF-8")+"="+URLEncoder.encode(d,"UTF-8")+"&"+
                        URLEncoder.encode("ans","UTF-8")+"="+URLEncoder.encode(ans,"UTF-8")+"&"+
                        URLEncoder.encode("quizname","UTF-8")+"="+URLEncoder.encode(quizname,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Question Added Succesfully";


            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("question_edit"))
        {
            reg_url="http://192.168.43.167/cms/question_edit.php";

            String question = params[1];
            String a =params[2];
            String b = params[3];
            String c = params[4];
            String d = params[5];
            String ans = params[6];
            String quizname = params[7];
            String cond_que = params[8];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("question","UTF-8")+"="+URLEncoder.encode(question,"UTF-8")+"&"+
                        URLEncoder.encode("a","UTF-8")+"="+URLEncoder.encode(a,"UTF-8")+"&"+
                        URLEncoder.encode("b","UTF-8")+"="+URLEncoder.encode(b,"UTF-8")+"&"+
                        URLEncoder.encode("c","UTF-8")+"="+URLEncoder.encode(c,"UTF-8")+"&"+
                        URLEncoder.encode("d","UTF-8")+"="+URLEncoder.encode(d,"UTF-8")+"&"+
                        URLEncoder.encode("ans","UTF-8")+"="+URLEncoder.encode(ans,"UTF-8")+"&"+
                        URLEncoder.encode("quizname","UTF-8")+"="+URLEncoder.encode(quizname,"UTF-8")+"&"+
                        URLEncoder.encode("cond_que","UTF-8")+"="+URLEncoder.encode(cond_que,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Question Updated Succesfully";


            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("question_delete"))
        {
            reg_url="http://192.168.43.167/cms/question_delete.php";

            String quizname = params[1];
            String cond_que = params[2];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("quizname","UTF-8")+"="+URLEncoder.encode(quizname,"UTF-8")+"&"+
                        URLEncoder.encode("cond_que","UTF-8")+"="+URLEncoder.encode(cond_que,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                return "Question Deleted Succesfully";

            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public BackgroundTask(View.OnClickListener onClickListener) {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
