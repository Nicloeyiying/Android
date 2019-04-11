package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addText(View v){
        try{
            FileOutputStream fileOutputStream = openFileOutput("first_demo",
                    ((CheckBox) findViewById(R.id.appendCheck)).isChecked() ?
                            Context.MODE_APPEND:Context.MODE_PRIVATE);
            fileOutputStream.write(((EditText) findViewById(R.id.addText))
                    .getText().toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            ((EditText)findViewById(R.id.addText)).setText("");
            Toast toast  = Toast.makeText(MainActivity.this,"add success",
                    Toast.LENGTH_SHORT);
            toast.show();
        }catch (java.io.IOException e){
            e.printStackTrace();
        }
    }
    public void readText(View v){
        try{
            FileInputStream fileInputStream = openFileInput("file_demo");
            byte[] bytes = new byte[fileInputStream.available()];
            while (fileInputStream.read(bytes)!=-1){
                TextView show = (TextView)findViewById(R.id.showText);
                show.setText(new String(bytes));
            }
        }catch (java.io.IOException e){
            e.printStackTrace();
        }
    }

//        public void addText(View v){
//        try {
//            FileOutputStream fileOutputStream = openFileOutput("file_demo",
//                    ((CheckBox)findViewById(R.id.appendCheck))
//                            .isChecked() ?
//                            Context.MODE_APPEND : Context.MODE_PRIVATE);
//            fileOutputStream.write(((EditText)findViewById(R.id.addText))
//                    .getText().toString().getBytes());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            ((EditText)findViewById(R.id.addText)).setText("");
//            Toast toast = Toast.makeText(this, "add success", Toast.LENGTH_SHORT);
//            toast.show();
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void readText(View v){
//        try {
//            FileInputStream fileInputStream = openFileInput("file_demo");
//            byte[] bytes = new byte[fileInputStream.available()];
//            while (fileInputStream.read(bytes) != -1){
//                TextView textView = (TextView)findViewById(R.id.showText);
//                textView.setText(new String(bytes));
//            }
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
//    }
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
