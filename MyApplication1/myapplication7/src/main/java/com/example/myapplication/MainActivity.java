package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.http_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            EditText editText = (EditText)findViewById(R.id.username);
                            EditText editText1 = (EditText) findViewById(R.id.passward);
                            StringBuilder stringBuilder = new StringBuilder("http://119.29.60.170/index.aspx?type=login");
                            stringBuilder.append("&username="+editText.getText().toString()+"&password="+editText1.getText().toString());
                            URL url = new URL(stringBuilder.toString());
                            //119.29.60.170/index.aspx?type=regist&username=ying&password=123456&email=1111111111@qq.com&phone=15555555555
//                            URL url = new URL("http://119.29.60.170/index.aspx?type=login&username=ying&password=123456");
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                            httpURLConnection.setConnectTimeout(5*1000);
//                            httpURLConnection.setRequestMethod("POST");
//                            httpURLConnection.setDoOutput(true);
//                            String body = "username=" + URLEncoder.encode(username, "utf-8")
//                                    + "&password=" + URLEncoder.encode(password, "utf-8");
//                            httpURLConnection.getOutputStream().write(body.getBytes());
                            InputStream inputStream = httpURLConnection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            StringBuffer stringBuffer = new StringBuffer();
                            String temp;
                            while ((temp = bufferedReader.readLine()) != null) {
                                stringBuffer.append(temp);
                            }
                            if (stringBuffer.toString().equals("login success")){
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                Looper.loop();
                            }else if (stringBuffer.toString().equals("login failed")){
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                            Message message = new Message();
                            message.obj = stringBuffer.toString();
                            handler.sendMessage(message);
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ((TextView)findViewById(R.id.show_http)).setText(msg.obj.toString());
        }
    };

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
