package com.example.circleoffriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter {
    private int re;

    public ListViewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        re = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Status status = (Status) getItem(position);
        ListLayout listLayout = new ListLayout();
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(re, null);
            listLayout.nameView = (TextView) view.findViewById(R.id.names);
            listLayout.contentView = (TextView) view.findViewById(R.id.content);
            listLayout.timeView = (TextView) view.findViewById(R.id.time);
            listLayout.imageView = (ImageView) view.findViewById(R.id.img);
            listLayout.imageView1 = (ImageView) view.findViewById(R.id.img1);
            listLayout.imageView2 = (ImageView) view.findViewById(R.id.img2);
            listLayout.imageView3 = (ImageView) view.findViewById(R.id.img3);
            view.setTag(listLayout);
        } else {
            view = convertView;
            listLayout = (ListLayout) view.getTag();
        }
//        listLayout.nameView.setText(status.getName());
//        listLayout.contentView.setText(status.getContent());
//        listLayout.timeView.setText(status.getTime());
//        listLayout.imageView.setImageResource(status.getImg());
//        listLayout.imageView1.setImageResource(status.getImg1());
//        listLayout.imageView2.setImageResource(status.getImg2());
//        listLayout.imageView3.setImageResource(status.getImg3());
        return view;
    }

    class ListLayout {
        private TextView nameView;
        private TextView contentView;
        private TextView timeView;
        private ImageView imageView;
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
    }
}