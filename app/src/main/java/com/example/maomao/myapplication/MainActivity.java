package com.example.maomao.myapplication;

import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.maomao.myapplication.recycler.Discuss;
import com.example.maomao.myapplication.recycler.DiscussAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:34
 * modify developer：  admin
 * modify time：11:34
 * modify remark：
 *
 * @version 2.0
 */


public class MainActivity extends AppCompatActivity {
    public static final String CHAT_TABLE = "chatTable";//表名
    public static final String CHAT_COMMENT = "chat_comment";
    public static final String HEADER_URL = "header_url";
    public static final String TIME = "time";
    public static final String USER_ID = "user_id";
    public static final String TO_USER_ID = "to_user_id";
    public static final String ID = "id";
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private CoordinatorLayout rootLayout;
    private FloatingActionButton fabBtn;
    private SQLiteDatabase sqLiteDatabase;
    private RecyclerView recycler;
    private SwipeRefreshLayout swipe_refresh;
    private DiscussAdapter adapter;
    private List<Discuss> dataList = new ArrayList<>();
    private int nowId = -1;
    private LinearLayoutManager layoutManager;
    private DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        HashMap<String, String> data_name = new HashMap<>();
        data_name.put(ID, "id");
        data_name.put(HEADER_URL, "String");
        data_name.put(USER_ID, "int");
        data_name.put(TO_USER_ID, "int");
        data_name.put(TIME, "int");
        data_name.put(CHAT_COMMENT, "chat");
        helper = new DataBaseHelper(this, "chat.db", null, 1, CHAT_TABLE, data_name);
        sqLiteDatabase = helper.getWritableDatabase();
        initToolbar();
        initInstances();
        recycler = (RecyclerView) findViewById(R.id.recycler);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        initData();
        adapter = new DiscussAdapter(getBaseContext(), dataList);
        layoutManager= new LinearLayoutManager(getBaseContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        swipe_refresh.setOnRefreshListener(() -> {
            recycler.removeAllViews();
            Toast.makeText(getBaseContext(), "refresh", Toast.LENGTH_LONG).show();
            swipe_refresh.setRefreshing(false);
            initData();
            adapter = new DiscussAdapter(getBaseContext(), dataList);
            recycler.setAdapter(adapter);
            MoveToPosition(layoutManager,recycler,10);
        });

    }
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }
    private void initData() {
        Cursor cursor;
        if (nowId != -1)
            cursor = sqLiteDatabase.query(CHAT_TABLE, new String[]{}, USER_ID + "=? and "+ ID+"<?", new String[]{"709558",""+nowId}, null, null, "id desc", ("" + 10));
        else
            cursor = sqLiteDatabase.query(CHAT_TABLE, new String[]{}, USER_ID + "=? ", new String[]{"709558"}, null, null, "id desc", ("" + 10));
        if (cursor.moveToFirst()) {
            do {
                Discuss discuss = new Discuss();
                discuss.setChat_comment(cursor.getString(cursor.getColumnIndexOrThrow(CHAT_COMMENT)));
                discuss.setHeader_url(cursor.getString(cursor.getColumnIndexOrThrow(HEADER_URL)));
                discuss.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                discuss.setTime(cursor.getLong(cursor.getColumnIndexOrThrow(TIME)));
                discuss.setUser_id(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
                discuss.setTo_user_id(cursor.getInt(cursor.getColumnIndexOrThrow(TO_USER_ID)));
                dataList.add(0, discuss);
            } while (cursor.moveToNext());
            nowId = dataList.get(0).getId();
        }
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initInstances() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
//        左上角的返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Hello. I am Snackbar!", Snackbar.LENGTH_LONG)
                        .setAction("Undo1", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        });
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("Design Library");
    }

    //    左上角的按钮变换
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //    右边的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //菜单点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_info:
                Toast.makeText(this, "info", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddClick(View view) {

        //update
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHAT_COMMENT, "毛毛的聊天内容啊啊啊啊");
        contentValues.put(HEADER_URL, "http://img0.imgtn.bdimg.com/it/u=577849643,3398822449&fm=11&gp=0.jpg");
        contentValues.put(TIME, "" + System.currentTimeMillis());
        contentValues.put(USER_ID, 709558);
        contentValues.put(TO_USER_ID, 100011);

        sqLiteDatabase.insert(CHAT_TABLE, null, contentValues);

    }

    public void onInitDataClick(View view) {
        for (int i = 0; i < 10; i++) {


            ContentValues contentValues = new ContentValues();
            contentValues.put(CHAT_COMMENT, "comment" + i);
            contentValues.put(HEADER_URL, "header" + i);
            contentValues.put(TIME, i);
            contentValues.put(USER_ID, i);
            contentValues.put(TO_USER_ID, i);
            sqLiteDatabase.insert(CHAT_TABLE, null, contentValues);
        }


    }
}
