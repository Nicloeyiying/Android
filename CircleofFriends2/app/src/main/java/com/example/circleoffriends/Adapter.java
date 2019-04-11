package com.example.circleoffriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.network.zhouwei.http_network.Comment;

import java.util.List;


public class Adapter extends ArrayAdapter {
    private int resource_id=-1;

    public Adapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resource_id = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Comment comment=(Comment)this.getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resource_id, null);
        TextView username=(TextView)view.findViewById(R.id.textView_username);
        username.setText(comment.getUsername());
        TextView context=(TextView)view.findViewById(R.id.textView_context);
        context.setText(comment.getContext());
        return view;
    }

}
