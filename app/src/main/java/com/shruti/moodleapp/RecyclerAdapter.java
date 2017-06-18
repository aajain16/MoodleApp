package com.shruti.moodleapp;

/**
 * Created by Shade on 5/9/2016.
 */

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] subject = {"DBMS",
            "OS",
            "MAD"};
    private String[] year = {"2017",
            "2014",
            "2018"};
    private String[] branch = {"IT",
            "CE",
            "CE"};

    private int[] edit = {
            R.mipmap.ic_edit,
            R.mipmap.ic_edit,
            R.mipmap.ic_edit,
             };
    private int[] delete = {
            R.mipmap.ic_delete,
            R.mipmap.ic_delete,
            R.mipmap.ic_delete,

    };

//    private String[] titles = {"Chapter One",
//            "Chapter Two",
//            "Chapter Three",
//            "Chapter Four",
//            "Chapter Five",
//            "Chapter Six",
//            "Chapter Seven",
//            "Chapter Eight"};

//    private String[] details = {"Item one details",
//            "Item two details", "Item three details",
//            "Item four details", "Item file details",
//            "Item six details", "Item seven details",
//            "Item eight details"};

//    private int[] images = { R.drawable.android_image_1,
//            R.drawable.android_image_2,
//            R.drawable.android_image_3,
//            R.drawable.android_image_4,
//            R.drawable.android_image_5,
//            R.drawable.android_image_6,
//            R.drawable.android_image_7,
//            R.drawable.android_image_8 };

    class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public TextView tv_sub, tv_year, tv_branch;
        public ImageView iv_edit,iv_delete;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_sub = (TextView) itemView.findViewById(R.id.tv_sub);
            tv_year = (TextView) itemView.findViewById(R.id.tv_year);
            tv_branch = (TextView) itemView.findViewById(R.id.tv_branch);
            iv_edit=(ImageView)itemView.findViewById(R.id.iv_edit);
            iv_delete = (ImageView)itemView.findViewById(R.id.iv_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tv_sub.setText(subject[i]);
        viewHolder.tv_year.setText(year[i]);
        viewHolder.tv_branch.setText(branch[i]);
        viewHolder.iv_edit.setImageResource(edit[i]);
        viewHolder.iv_delete.setImageResource(delete[i]);
    }

   @Override
    public int getItemCount() {
//       return 0;
       return subject.length;
    }
}