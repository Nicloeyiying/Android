package com.example.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fileInputStream = openFileInput("ofo_test1");
                    byte[] bytes = new byte[fileInputStream.available()];
                    while(fileInputStream.read(bytes)!=-1){
                        Log.i("读取到的数据：",new String(bytes));
                    }
                }catch (java.io.IOException e){
                    e.printStackTrace();
                }
                try{
                    FileOutputStream fileOutputStream = openFileOutput("ofo_test1",MODE_PRIVATE);
                    fileOutputStream.write("sone data".getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }catch (java.io.IOException e){
                    e.printStackTrace();
                }
//                try {
//                    FileInputStream fileInputStream = openFileInput("ofo_test1");
//                    byte[] bytes = new byte[fileInputStream.available()];
//                    while (fileInputStream.read(bytes) != -1){
//                        Log.i("文件读取结果：",new String(bytes));
//                    }
//                } catch (java.io.IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    FileOutputStream fileOutputStream =  openFileOutput("ofo_test1",
//                            Context.MODE_PRIVATE);
//                    fileOutputStream.write("some data".getBytes());
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//                } catch (java.io.IOException e) {
//                    e.printStackTrace();
//                }
//
//                /*SharedPreferences sharedPreferences = getSharedPreferences(
//                        "wg_jk_test1", Context.MODE_PRIVATE);
//                //取
//                Log.i("读取shared数据： ", sharedPreferences.getString(
//                        "key", "defalut_value"));
//                //写
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("test_key", "test_value");
//                editor.apply();*/
//            }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
