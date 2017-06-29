package com.shruti.moodleapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jayesh on 19/6/17.
 */

public class qlistAdapter extends BaseAdapter implements ListAdapter{

    private ArrayList<String[]> list = new ArrayList<String[]>();
    private Context context;

    String que,a,b,c,d;



    public qlistAdapter(ArrayList<String[]> list, Context context) {
        this.list = (ArrayList<String[]>) list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.qlist_layout, null);
        }

        for (String[] strArr : list) {
            String que = strArr[0];
            String a = strArr[1];
            String b = strArr[2];
            String c = strArr[3];
            String d = strArr[4];

//            //Handle TextView and display string from your list
//            tv_q = (TextView)view.findViewById(R.id.tv_q);
//            tv_q.setText(que);
//            tv_A = (TextView)view.findViewById(R.id.tv_A);
//            tv_A.setText(a);
//            tv_B = (TextView)view.findViewById(R.id.tv_B);
//            tv_B.setText(b);
//            tv_C = (TextView)view.findViewById(R.id.tv_C);
//            tv_C.setText(c);
//            tv_D = (TextView)view.findViewById(R.id.tv_D);
//            tv_D.setText(d);

            //Handle TextView and display string from your list
            final TextView tv_q = (TextView)view.findViewById(R.id.tv_q);
            tv_q.setText(que);
            final TextView tv_A = (TextView)view.findViewById(R.id.tv_A);
            tv_A.setText(a);
            final TextView tv_B = (TextView)view.findViewById(R.id.tv_B);
            tv_B.setText(b);
            final TextView tv_C = (TextView)view.findViewById(R.id.tv_C);
            tv_C.setText(c);
            final TextView tv_D = (TextView)view.findViewById(R.id.tv_D);
            tv_D.setText(d);
        }



        //Handle buttons and add onClickListeners
        Button btn_remove = (Button)view.findViewById(R.id.btn_remove);
        Button btn_edit = (Button)view.findViewById(R.id.btn_edit);

        btn_remove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Intent i = new Intent(context,Questions_edit.class);
                i.putExtra("id",position);
//                i.putExtra("que",tv_q.getText().toString());
//                i.putExtra("A",tv_A.getText().toString());
//                i.putExtra("B",tv_B.getText().toString());
//                i.putExtra("C",tv_C.getText().toString());
//                i.putExtra("D",tv_D.getText().toString());
                context.startActivity(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
