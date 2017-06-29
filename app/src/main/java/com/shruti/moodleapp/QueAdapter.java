package com.shruti.moodleapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import static com.shruti.moodleapp.Quiz_edit.quizname;

/**
 * Created by jayesh on 21/6/17.
 */

public class QueAdapter extends ArrayAdapter<String> {

    Context context;
    String[] questions,a,b,c,d;
    QueAdapter(Context context, String[] questions, String[] a, String[] b, String[] c, String[] d)
    {
        super(context, R.layout.qlist_layout,R.id.tv_q,questions);
        this.context = context;
        this.questions = questions;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.qlist_layout,parent,false);

        final TextView tv_q = (TextView) row.findViewById(R.id.tv_q);
        final TextView tv_A = (TextView) row.findViewById(R.id.tv_A);
        final TextView tv_B = (TextView) row.findViewById(R.id.tv_B);
        final TextView tv_C = (TextView) row.findViewById(R.id.tv_C);
        final TextView tv_D = (TextView) row.findViewById(R.id.tv_D);

        tv_q.setText(questions[position]);
        tv_A.setText(a[position]);
        tv_B.setText(b[position]);
        tv_C.setText(c[position]);
        tv_D.setText(d[position]);

        Button btn_remove = (Button)row.findViewById(R.id.btn_remove);
        Button btn_edit = (Button)row.findViewById(R.id.btn_edit);

        btn_remove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                String method="question_delete";
                BackgroundTask backgroundTask=new BackgroundTask(context);
                backgroundTask.execute(method, quizname, questions[position]);
                notifyDataSetChanged();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Intent i = new Intent(context,Questions_edit.class);
                i.putExtra("id",position);
                i.putExtra("que",tv_q.getText().toString());
                i.putExtra("A",tv_A.getText().toString());
                i.putExtra("B",tv_B.getText().toString());
                i.putExtra("C",tv_C.getText().toString());
                i.putExtra("D",tv_D.getText().toString());
                context.startActivity(i);
                notifyDataSetChanged();
            }
        });

        return row;
    }
}
