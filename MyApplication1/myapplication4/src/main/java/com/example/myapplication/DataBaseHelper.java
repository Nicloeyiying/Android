package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
    public DataBaseHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version){
        super(context,name,factory,version);
    }
    public  void onCreate(SQLiteDatabase db){
        db.execSQL("create table word(id integer primary key autoincrement," +
                "English varchar(20),Chinese varchar(20))");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
