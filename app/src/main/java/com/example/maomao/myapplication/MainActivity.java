package com.example.maomao.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView view = (TextView) findViewById(R.id.main_tv_text);
        view.setOnClickListener(v -> {
            Toast.makeText(this,"",Toast.LENGTH_LONG).show();});
    }
}
