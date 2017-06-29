package com.shruti.moodleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by jayesh on 28/6/17.
 */

public class TestQuizAdapter extends ArrayAdapter<String> {

    Context context;
    String[] questions,a,b,c,d;
    TestQuizAdapter(Context context, String[] questions, String[] a, String[] b, String[] c, String[] d)
    {
        super(context, R.layout.quiztest_layout,R.id.tv_que,questions);
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
        View row = inflater.inflate(R.layout.quiztest_layout,parent,false);

        final TextView tv_que = (TextView) row.findViewById(R.id.tv_que);
        final TextView tv_opta = (TextView) row.findViewById(R.id.tv_opta);
        final TextView tv_optb = (TextView) row.findViewById(R.id.tv_optb);
        final TextView tv_optc = (TextView) row.findViewById(R.id.tv_optc);
        final TextView tv_optd = (TextView) row.findViewById(R.id.tv_optd);

        tv_que.setText(questions[position]);
        tv_opta.setText(a[position]);
        tv_optb.setText(b[position]);
        tv_optc.setText(c[position]);
        tv_optd.setText(d[position]);

        return row;
    }
}
