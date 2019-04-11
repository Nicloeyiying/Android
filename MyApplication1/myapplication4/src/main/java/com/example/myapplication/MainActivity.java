package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.DataBaseHelper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends Activity {
    private DataBaseHelper dataBaseHelper = new DataBaseHelper(this,"word.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.relativeLayout);
        v.getBackground().setAlpha(150);
    }
    public void insert(View v){
        EditText enText = (EditText)findViewById(R.id.En_editText);
        EditText chText = (EditText)findViewById(R.id.Ch_editText);
        ContentValues contentValues = new ContentValues();
        contentValues.put("English",enText.getText().toString());
        contentValues.put("Chinese", chText.getText().toString());
        Cursor cursor = dataBaseHelper.getReadableDatabase().query("word",null,
                "English like? or Chinese like?",new String[]{"%"+enText.getText().toString()+"%",
                        "%"+chText.getText().toString()+"%"},null,null,null);
        if(cursor.getCount()==0){
            dataBaseHelper.getWritableDatabase().insert("word", null, contentValues);
            Toast.makeText(this,"插入成功！",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"数据已存在！",Toast.LENGTH_SHORT).show();
        }
        enText.setText("");
        chText.setText("");
    }
   public void search(View v){
       String search = ((EditText)findViewById(R.id.Se_editText)).getText().toString();
       TextView show = (TextView)findViewById(R.id.show);
       Cursor cursor = dataBaseHelper.getReadableDatabase().query("word",null,
               "English like? or Chinese like?",new String[]{"%"+search+"%",
                       "%"+search+"%"},null,null,null);
       String result = "";
       while (cursor.moveToNext()) {
               result += cursor.getInt(0)+":"+cursor.getString(1)+":"+cursor.getString(2)+".";
       }
       if(result.equals("")){
           Toast.makeText(this,"数据不存在！",Toast.LENGTH_SHORT).show();
       }else {
           show.setText(result);
       }
       cursor.close();
   }
    public void delete(View v){
        EditText search = (EditText)findViewById(R.id.Se_editText);
        TextView show = (TextView)findViewById(R.id.show);
        Cursor cursor = dataBaseHelper.getReadableDatabase().query("word",null,
                "English like? or Chinese like?",new String[]{"%"+search.getText().toString()+"%",
                        "%"+search.getText().toString()+"%"},null,null,null);
        if(cursor.getCount()!=0){
            dataBaseHelper.getWritableDatabase().delete("word", "English like? or Chinese like?",
                    new String[]{"%" + search.getText().toString() + "%",
                            "%" + search.getText().toString() + "%"});
            Toast.makeText(this,"数据已删除！",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"数据不存在！",Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
    public void update(View v){
        String search = ((EditText)findViewById(R.id.Se_editText)).getText().toString();
        Cursor cursor = dataBaseHelper.getReadableDatabase().query("word", null,
                "English like? or Chinese like?", new String[]{"%" + search + "%",
                        "%" + search + "%"}, null, null, null);
        if(cursor.moveToFirst()){
            ((EditText)findViewById(R.id.En_editText)).setText(cursor.getString(1));
            ((EditText)findViewById(R.id.Ch_editText)).setText(cursor.getString(2));
            ((TextView)findViewById(R.id.show)).setText("");
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
