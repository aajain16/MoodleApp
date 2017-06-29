package com.shruti.moodleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by OM on 6/28/2017.
 */

public class ClassAdapter extends ArrayAdapter<String>
{
    Context context;
    String[] sub,branch,sem;
    ClassAdapter(Context context, String[] sub, String[] branch, String[] sem)
    {
        super(context, R.layout.class_layout,R.id.tv_sub,sub);
        this.context = context;
        this.sub = sub;
        this.branch = branch;
        this.sem = sem;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.class_layout, parent, false);

        final TextView tv_sub = (TextView) row.findViewById(R.id.tv_sub);
        final TextView tv_branch = (TextView) row.findViewById(R.id.tv_branch);
        final TextView tv_sem = (TextView) row.findViewById(R.id.tv_sem);


        tv_sub.setText(sub[position]);
        tv_branch.setText(branch[position]);
        tv_sem.setText(sem[position]);

        ImageView iv_edit = (ImageView) row.findViewById(R.id.iv_edit);
        ImageView iv_remove = (ImageView) row.findViewById(R.id.iv_remove);

        iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return row;
    }
}
