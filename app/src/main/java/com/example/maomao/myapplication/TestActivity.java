package com.example.maomao.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView view = (TextView) findViewById(R.id.main_tv_text);
        view.setOnClickListener(v -> {
            Toast.makeText(this,"",Toast.LENGTH_LONG).show();});
    }
    public void onTestClick(View view){
        Toast.makeText(getBaseContext(),"sdsds",Toast.LENGTH_LONG).show();
    }
}
