package com.example.administrator.highlevel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ten_five_feng extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_feng);
    }
    public void fanhui(View view){
        finish();
    }
    public void next(View view){
        Intent intent=new Intent(this,ten_six_santan.class);
        startActivity(intent);
        finish();
    }
}
