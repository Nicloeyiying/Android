package com.example.circleoffriends;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class regist extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        View v = findViewById(R.id.bg2);
        v.getBackground().setAlpha(150);
        textView = (TextView) findViewById(R.id.xieyi);
        textView.setText(getClickableSpan());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        final ImageView img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.ic_head);
            }
        });
        Button button = (Button) findViewById(R.id.regist);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        try {
                            EditText name = (EditText) findViewById(R.id.name);
                            EditText password = (EditText) findViewById(R.id.password);
                            EditText phone = (EditText) findViewById(R.id.phone);
                            EditText email = (EditText) findViewById(R.id.email);
                            StringBuilder stringBuilder = new StringBuilder("http://119.29.60.170/index.aspx?type=regist");
                            stringBuilder.append("&username=" + name.getText().toString() + "&password=" + password.getText().toString() + "&email=" + email.getText().toString() + "&phone=" + phone.getText().toString());
                            URL url = new URL(stringBuilder.toString());
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.connect();
                            if (httpURLConnection.getResponseCode() == 200) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                StringBuffer stringBuffer = new StringBuffer();
                                String temp;
                                while ((temp = bufferedReader.readLine()) != null) {
                                    stringBuffer.append(temp);
                                }
                                if (stringBuffer.toString().equals("regist success")) {
                                    Looper.prepare();
                                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(regist.this, login.class);
                                    startActivity(intent);
                                    regist.this.finish();
                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(getApplicationContext(), stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                                Intent intent = new Intent(regist.this, login.class);
                                startActivity(intent);
                                regist.this.finish();
                            }
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    private SpannableString getClickableSpan() {
        SpannableString spanableInfo = new SpannableString("点击上面的“注册”按钮，即表示你同意《腾讯微信软件许可及服务协议》和《微信隐私保护指引》");
        spanableInfo.setSpan(new UnderlineSpan(), 18, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(regist.this, "《腾讯微信软件许可及服务协议》", Toast.LENGTH_SHORT).show();
            }
        }, 18, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new ForegroundColorSpan(Color.BLUE), 18, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(regist.this, "《微信隐私保护指引》", Toast.LENGTH_SHORT).show();
            }
        }, 34, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new ForegroundColorSpan(Color.BLUE), 34, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanableInfo;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regist, menu);
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

class Clickable extends ClickableSpan implements View.OnClickListener {
    private final View.OnClickListener mListener;

    public Clickable(View.OnClickListener listener) {
        mListener = listener;
    }

    public void onClick(View view) {
        mListener.onClick(view);
    }
}
