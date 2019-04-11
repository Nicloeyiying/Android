package com.example.circle;

import android.app.Activity;
import android.content.Intent;
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
        View v = findViewById(R.id.bg_publish);
        v.getBackground().setAlpha(150);
        TextView pub = (TextView) findViewById(R.id.pub_publish);
        pub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText context = (EditText) findViewById(R.id.context_publish);
                Intent intent1 = new Intent();
                intent1.putExtra("contexts", context.getText().toString());
                publish.this.setResult(RESULT_OK, intent1);
                publish.this.finish();
            }
        });
        TextView cancel = (TextView) findViewById(R.id.cancel_publish);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText context = (EditText) findViewById(R.id.context_publish);
                Intent intent1 = new Intent();
                intent1.putExtra("contexts", "");
                publish.this.setResult(RESULT_OK, intent1);
                publish.this.finish();
            }
        });
        ImageView images = (ImageView) findViewById(R.id.dcim_publish);
        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);
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
