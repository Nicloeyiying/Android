package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.relativeLayout);
        v.getBackground().setAlpha(150);
    }
    public void login(View v){
        EditText editText1 = (EditText) findViewById(R.id.nameEdit);
        EditText editText2 = (EditText) findViewById(R.id.passwordEdit);
        EditText editText3 = (EditText) findViewById(R.id.rePasswordEdit);
        final RadioGroup sex = (RadioGroup) findViewById(R.id.radioGroup);
        if (editText2.getText().toString().equals(editText3.getText().toString())){
            if(editText1.getText().toString().equals("")){
                Toast.makeText(this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
//                textView.setText();
//                textView.setVisibility(View.VISIBLE);
            }else if(editText2.getText().toString().equals("")){
//                textView.setText("密码不能为空！");
                Toast.makeText(this,"密码不能为空！",Toast.LENGTH_SHORT).show();
//                textView.setVisibility(View.VISIBLE);

            }else {
//                textView.setVisibility(View.INVISIBLE);
                String s1 = "用户名:" + editText1.getText().toString() + "\n密码:" + editText2.getText().toString() + "\n";
                int count  = 0;
                for (int i = 0; i < sex.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) sex.getChildAt(i);
                    if (radioButton.isChecked()) {
                        s1 += "性别：" + radioButton.getText().toString() + "\n";
                        count++;
                        break;
                    }
                }
                if(count==0){

                    s1 += "性别：保密"  + "\n";
                }
                CheckBox game = (CheckBox) findViewById(R.id.game);
                CheckBox book = (CheckBox) findViewById(R.id.book);
                CheckBox sports = (CheckBox) findViewById(R.id.sports);
                int sum=0;
                final List<CheckBox> checkBoxList = Arrays.asList(game, book, sports);
                for (CheckBox checkBox : checkBoxList) {
                    if (checkBox.isChecked()) {
                        s1 += "爱好：" + checkBox.getText().toString();
                        sum++;
                    }
                }
                if(sum==0){
                    s1+="爱好：无";
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("login", s1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }else{
//            textView.setText("密码不一致！");
//            textView.setVisibility(View.VISIBLE);
            Toast.makeText(this,"密码不一致！",Toast.LENGTH_SHORT).show();
            editText2.setText("");
            editText3.setText("");
        }
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
