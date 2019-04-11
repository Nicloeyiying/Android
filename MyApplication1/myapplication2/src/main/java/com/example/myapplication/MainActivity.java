package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//                try{
//                    FileInputStream fileInputStream = openFileInput("ofo_test1");
//                    byte[]bytes = new byte[fileInputStream.available()];
//                    while ((fileInputStream.read(bytes)!=-1)){
//                        Log.i("文件读取结果：",new String(bytes));
//                    }
//                }catch (java.io.IOException e){
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
