package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.bean.Product;
import com.example.myapplication.utils.ProductListHttpThread;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductListHttpThread productListHttpThread = new ProductListHttpThread();
        productListHttpThread.start();
        try {
            productListHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        List<Product> list = JSON.parseArray(productListHttpThread.getProductResult(), Product.class);
//        ProductAdapter productAdapter = new ProductAdapter(this, R.layout.product_list_view_layout, list);
//        ((ListView) findViewById(R.id.shop_list_view)).setAdapter(productAdapter);
        List<Product> list = Arrays.asList(
                new Product("","大甩卖","$2"),
                new Product("","特价","$20000")
        );
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
