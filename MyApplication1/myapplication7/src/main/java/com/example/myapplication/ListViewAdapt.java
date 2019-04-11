package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ListViewAdapt extends ArrayAdapter{
    private int re;
    public ListViewAdapt(Context context,int resource, List objects){
        super(context,resource,objects);
        re = resource;
    }
    public View getView(int position,View convertView,ViewGroup parent){
        Pro pro = (Pro)getItem(position);
        ListLayout listLayout = new ListLayout();
        View view;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(re,null);
            listLayout.titleView = (TextView) view.findViewById(R.id.title);
            listLayout.priceView = (TextView) view.findViewById(R.id.price);
            listLayout.imgView = (ImageView) view.findViewById(R.id.img);
            listLayout.numView = (TextView) view.findViewById(R.id.num);
            listLayout.statusView = (TextView) view.findViewById(R.id.status);
            listLayout.proTimeView = (TextView) view.findViewById(R.id.pro_time);
            listLayout.timeView = (TextView) view.findViewById(R.id.time);
            listLayout.imgButtonView = (ImageButton) view.findViewById(R.id.shop_button);
            view.setTag(listLayout);
        }else {
            view = convertView;
            listLayout = (ListLayout)view.getTag();
        }
        listLayout.titleView.setText(pro.getTitle());
        listLayout.priceView.setText(pro.getPrice());
        listLayout.imgView.setImageResource(pro.getImg());
        listLayout.numView.setText(pro.getNum());
        listLayout.statusView.setText(pro.getStatus());
        listLayout.proTimeView.setText(pro.getPro_time());
        listLayout.timeView.setText(pro.getTime());
        listLayout.imgButtonView.setImageResource(pro.getImgButton());
        return view;
    }
    class ListLayout{
        private TextView titleView;
        private TextView priceView;
        private ImageView imgView;
        private TextView numView;
        private TextView statusView;
        private TextView proTimeView;
        private TextView timeView;
        private ImageButton imgButtonView;
    }
}
