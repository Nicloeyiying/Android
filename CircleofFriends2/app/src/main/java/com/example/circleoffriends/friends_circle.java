package com.example.circleoffriends;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.network.zhouwei.http_network.Comment;
import com.network.zhouwei.http_network.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class friends_circle extends Activity {
    List<Status> list = new ArrayList<>();
    List<Status> lists;
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_circle);
        final TextView username = (TextView) findViewById(R.id.username);
        Intent intent = getIntent();
        username.setText(intent.getExtras().getString("logining"));
        List<Comment> comments;
        Server server=new Server();
        comments=server.getComments(0,10);
        Adapter adapter= new Adapter(this, R.layout.list_view_item, comments);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        ImageView publish = (ImageView) findViewById(R.id.publish);
//        Bitmap[] bms=new Bitmap[0];
//        server.commnetResourcesUpload("ying","ying test",bms);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(friends_circle.this, publish.class);
                startActivity(intent1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");
        TextView username = (TextView) findViewById(R.id.username);
        Status status = new Status(username.getText().toString(), result, "1分钟前", R.drawable.ic_head, R.drawable.tim_bg, 1, 1);
        lists.add(0, status);
        listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends_circle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
