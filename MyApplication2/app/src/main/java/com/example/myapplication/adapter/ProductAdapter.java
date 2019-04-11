package com.example.myapplication.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.bean.Product;
import com.example.myapplication.R;
import com.example.myapplication.utils.DataBaseHelp;
import com.example.myapplication.utils.ImageHttpThread;

import java.util.List;

public class ProductAdapter extends ArrayAdapter{
    private int resourceId;
    public ProductAdapter(Context context,int resource,List objects){
        super(context, resource,objects);
        resourceId = resource;
    }
    public View getView(int position,
                        View convertView,
                        ViewGroup parent){
        final Product product = (Product)getItem(position);
        ProductLayout productLayout = new ProductLayout();
        View view;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            productLayout.imgView = (ImageView)view.findViewById(R.id.product_img);
            productLayout.titleText = (TextView)view.findViewById(R.id.product_title);
            productLayout.priceText = (TextView)view.findViewById(R.id.product_price);
            productLayout.addShopButton = (Button) view.findViewById(R.id.add_shop_button);
            view.setTag(productLayout);
        }else{
            view = convertView;
            productLayout = (ProductLayout)view.getTag();
        }
        productLayout.titleText.setText(product.getTitle());
        productLayout.priceText.setText(product.getPrice());
        productLayout.addShopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataBaseHelp dataBaseHelp = new DataBaseHelp(getContext(), "shop.db", null, 1);
                Cursor cursor = dataBaseHelp.getReadableDatabase().query("tab_shop", null, "pro_id=?", new String[]{product.getId() + ""}, null, null, null);
                ContentValues contentValues = new ContentValues();
                contentValues.put("title", product.getTitle());
                contentValues.put("price", product.getPrice());
                contentValues.put("image", product.getImage());
                contentValues.put("pro_id", product.getId());
                if (cursor.moveToFirst()) {
                    contentValues.put("num", cursor.getInt(4) + 1);
                    dataBaseHelp.getWritableDatabase().update("tab_shop", contentValues, "pro_id = ?", new String[]{product.getId() + ""});
                } else {
                    contentValues.put("num", 1);
                    dataBaseHelp.getWritableDatabase().insert("tab_shop", null, contentValues);
                }
                Toast.makeText(getContext(), "添加购物车成功！", Toast.LENGTH_SHORT).show();
            }
        });
        ImageHttpThread imageHttpThread = new ImageHttpThread(product.getImage());
        imageHttpThread.start();
        try {
            imageHttpThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productLayout.imgView.setImageBitmap(imageHttpThread.getBitmap());
        return view;
    }
    class ProductLayout{
        private ImageView imgView;
        private TextView titleText;
        private TextView priceText;
        private Button addShopButton;
    }
}
