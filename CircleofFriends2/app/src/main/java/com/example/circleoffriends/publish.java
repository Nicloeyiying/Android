package com.example.circleoffriends;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class publish extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        View v = findViewById(R.id.bg3);
        v.getBackground().setAlpha(150);
        final TextView pub = (TextView) findViewById(R.id.pub);
        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText context = (EditText) findViewById(R.id.wenzi);
                Intent intent = new Intent();
                intent.putExtra("result", context.getText().toString());
                publish.this.setResult(RESULT_OK, intent);
                publish.this.finish();
            }
        });
//        ImageView img = (ImageView) findViewById(R.id.dicm);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent, 1);
//            }
//        });
        TextView ca = (TextView) findViewById(R.id.cancel);
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(publish.this, friends_circle.class);
                startActivity(intent);
                publish.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_publish, menu);
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
