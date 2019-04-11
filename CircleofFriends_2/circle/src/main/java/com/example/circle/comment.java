package com.example.circle;

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

import java.util.List;


public class comment extends Activity {
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        username = (TextView) findViewById(R.id.username_comment);
        final Intent intent = getIntent();
        username.setText(intent.getExtras().getString("logining"));
        List<Comment> comments;
        Server server = new Server();
        Comment comment = new Comment();
        server.imageFileDownload(server.getUserNameByUid(comment.getUsername()));
        comments = server.getComments(0, 10);
        Adapter adapter = new Adapter(this, R.layout.list_view_layout, comments);
        ListView listView = (ListView) findViewById(R.id.list_show);
        listView.setAdapter(adapter);
        ImageView publish_circle = (ImageView) findViewById(R.id.publish_comment);
        publish_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(comment.this, publish.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", username.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent1, 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("contexts");
        username = (TextView) findViewById(R.id.username_comment);
        ImageView head = (ImageView) findViewById(R.id.head_comment);
        if (result != null) {
            Server server = new Server();
            Bitmap[] bms = new Bitmap[0];
            server.commnetResourcesUpload(username.getText().toString(), result, bms);
            server.imageFileUpload(head, username.getText().toString());
        }
        List<Comment> comments;
        Server server = new Server();
        comments = server.getComments(0, 10);
        Adapter adapter = new Adapter(this, R.layout.list_view_layout, comments);
        ListView listView = (ListView) findViewById(R.id.list_show);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
