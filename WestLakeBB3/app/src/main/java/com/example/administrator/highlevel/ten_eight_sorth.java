package com.example.administrator.highlevel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ten_eight_sorth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight_sorth);
    }
    public void fanhui(View view){
        finish();
    }
    public void next(View view){
        Intent intent=new Intent(this,ten_nine_leifeng.class);
        startActivity(intent);
        finish();
    }
}
