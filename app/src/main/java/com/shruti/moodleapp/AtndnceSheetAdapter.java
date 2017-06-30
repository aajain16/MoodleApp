package com.shruti.moodleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

/**
 * Created by jayesh on 30/6/17.
 */

public class AtndnceSheetAdapter extends ArrayAdapter<String> {
    Context context;
    String[] student;
    AtndnceSheetAdapter(Context context, String[] student)
    {
        super(context, R.layout.atndncesheet_layout,R.id.chekedtv,student);
        this.context = context;
        this.student = student;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.atndncesheet_layout, parent, false);

        final CheckedTextView chekedtv = (CheckedTextView) row.findViewById(R.id.chekedtv);
        chekedtv.setText(student[position]);
        chekedtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CheckedTextView) view).toggle();
            }
        });

        return row;
    }
}


