package com.example.myapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelp extends SQLiteOpenHelper {
    public DataBaseHelp(Context context,String name,SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table tab_shop(id integer primary key autoincrement, title varchar(30),price varchar(30),image varchar(30),num integer,pro_id integer)");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
