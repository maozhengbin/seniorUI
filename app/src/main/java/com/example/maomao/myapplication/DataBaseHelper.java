package com.example.maomao.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.maomao.myapplication.recycler.Discuss;
import com.example.maomao.myapplication.recycler.Dto;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by maomao on 2017/9/4.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private String table_name;

    private static String creatUrl = "create table ";
    private HashMap<String, String> data_name;


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String table_name, HashMap<String, String> data_name) {
        super(context, name, factory, version);
        this.table_name = table_name;
        this.data_name = data_name;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        creatUrl += table_name + "(";
        for (String map : data_name.keySet()) {
            if (data_name.get(map).equals("String")) {
                creatUrl += map + " varchar(100),";
            } else if (data_name.get(map).equals("int")) {
                creatUrl += map + " integer,";

            } else if (data_name.get(map).equals("id")) {
                creatUrl += map + "  integer primary key not null,";

            } else if (data_name.get(map).equals("chat")) {
                creatUrl += map + " varchar(2000) not null,";
            }
        }
        creatUrl = creatUrl.substring(0, creatUrl.length() - 1);
        creatUrl += ");";
        Log.i("TEXTMAOMAO", creatUrl);
        db.execSQL(creatUrl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
