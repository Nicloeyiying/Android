package com.example.circle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.network.zhouwei.http_network.Comment;
import com.network.zhouwei.http_network.Server;

import java.util.List;

public class Adapter extends ArrayAdapter {
    private int resourceId;

    public Adapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Server server = new Server();
        Comment comment = (Comment) this.getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView username = (TextView) view.findViewById(R.id.name_list);
        username.setText(comment.getUsername());
        TextView context = (TextView) view.findViewById(R.id.context_list);
        context.setText(comment.getContext());
        ImageView img1 = (ImageView) view.findViewById(R.id.img_head);
        img1.setImageBitmap(server.imageFileDownload(comment.getUsername()));
        return view;
    }
}
