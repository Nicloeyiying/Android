package com.example.circleoffriends;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.xieyi);
        textView.setText(getClickableSpan());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Button button = (Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run(){
                        try {
                            EditText name = (EditText)findViewById(R.id.name);
                            EditText password = (EditText) findViewById(R.id.password);
                            EditText phone = (EditText) findViewById(R.id.phone);
                            //http://119.29.60.170/index.aspx?type=regist&username=xcy&password=123456&email=1186671277@qq.com&phone=17746624056
                            StringBuilder stringBuilder = new StringBuilder("http://119.29.60.170/index.aspx?type=regist");
                            stringBuilder.append("&username="+name.getText().toString()+"&password="+password.getText().toString()+"&email=1628111111@qq.com&phone="+phone.getText().toString());
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
                            if (stringBuffer.toString().equals("login success")){
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }else if (stringBuffer.toString().equals("login failed")){
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }catch (java.io.IOException e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    private SpannableString getClickableSpan(){
        SpannableString spanableInfo = new SpannableString("点击上面的“注册”按钮，即表示你同意《腾讯微信软件许可及服务协议》和《微信隐私保护指引》");
        spanableInfo.setSpan(new UnderlineSpan(),18,33,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"《腾讯微信软件许可及服务协议》",Toast.LENGTH_SHORT).show();
            }
        },18,33,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spanableInfo.setSpan(new Clickable(listener),start,end, Spanned.SPAN_MARK_MARK);
        spanableInfo.setSpan(new ForegroundColorSpan(Color.BLUE),18,33,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this,"《微信隐私保护指引》",Toast.LENGTH_SHORT).show();
            }
        },34,44,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spanableInfo.setSpan(new Clickable(listener),34,44,Spanned.SPAN_MARK_MARK);
        spanableInfo.setSpan(new ForegroundColorSpan(Color.BLUE),34,44,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanableInfo;
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
class Clickable extends ClickableSpan implements View.OnClickListener{
    private final View.OnClickListener mListener;
    public Clickable(View.OnClickListener listener){
        mListener = listener;
    }
    public void onClick(View view){
        mListener.onClick(view);
    }
}