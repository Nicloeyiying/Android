package com.example.circleoffriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class login extends Activity {
    EditText users;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View v = findViewById(R.id.bg1);
        v.getBackground().setAlpha(150);
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        try {
                            users = (EditText) findViewById(R.id.user);
                            password = (EditText) findViewById(R.id.pass);
                            String str1 = users.getText().toString();
                            String str2 = password.getText().toString();
                            StringBuilder stringBuilder = new StringBuilder("http://119.29.60.170/index.aspx?type=login");
                            if (str1.matches("[0-9]*")) {
                                stringBuilder.append("&phone=" + str1 + "&password=" + password.getText().toString());
                            } else if (str1.matches("[a-zA-Z]*")) {
                                stringBuilder.append("&username=" + str1 + "&password=" + password.getText().toString());
                            } else if (str1.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                                stringBuilder.append("&email=" + str1 + "&password=" + password.getText().toString());
                            }
                            URL url = new URL(stringBuilder.toString());
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            StringBuffer stringBuffer = new StringBuffer();
                            String temp;
                            while ((temp = bufferedReader.readLine()) != null) {
                                stringBuffer.append(temp);
                            }
                            if (stringBuffer.toString().equals("login success")) {
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this, friends_circle.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("logining", users.getText().toString());
                                intent.putExtras(bundle);
                                startActivity(intent);
                                Looper.loop();
                            } else if (stringBuffer.toString().equals("login failed")) {
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        final TextView regist = (TextView) findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, regist.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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