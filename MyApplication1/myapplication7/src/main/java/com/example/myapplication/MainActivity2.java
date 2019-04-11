package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;


public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        List<Pro> list = Arrays.asList(
                new Pro("订单号：356689878998", "已完成", R.drawable.car, "本田飞度 两厢 1.5 自动挡 五座", "￥2000","2018.01.08-2018.01.10","2天", R.drawable.btn_back),
                new Pro("订单号：356689878998", "已完成", R.drawable.car, "本田飞度 两厢 1.5 自动挡 五座", "￥2000","2018.01.08-2018.01.10","2天", R.drawable.btn_back),
                new Pro("订单号：356689878998", "已完成", R.drawable.car, "本田飞度 两厢 1.5 自动挡 五座", "￥2000","2018.01.08-2018.01.10","2天", R.drawable.btn_back)
        );
        ListViewAdapt adapter = new ListViewAdapt(this, R.layout.list_view_first_layout, list);
        ListView listView = ((ListView) findViewById(R.id.show_list));
        listView.setAdapter((ListAdapter) adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view.findViewById(R.id.title)).getText().toString(), Toast.LENGTH_SHORT).show();
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
