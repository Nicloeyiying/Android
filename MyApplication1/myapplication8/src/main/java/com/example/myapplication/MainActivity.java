package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =(TextView) this.findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {//点击该按钮会打开一个新的Activity
            public void onClick(View v) {
                //第二个参数为请求码，可以根据业务需求自己编号
                startActivityForResult(new Intent(MainActivity.this, MainActivity2.class), 1);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textView =(TextView) this.findViewById(R.id.text);
        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
        textView.setText(result);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
