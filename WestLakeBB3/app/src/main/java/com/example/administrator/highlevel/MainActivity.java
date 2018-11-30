package com.example.administrator.highlevel;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.fragment_Introduce:
                    Fragment fragment=new FragmentIntroduce();
                    transaction.replace(R.id.content,fragment);
                    transaction.commit();
                    return true;
                case R.id.fragment_Trailer:
                    Fragment fragmentTrailer=new FragmentTrailer();
                    transaction.replace(R.id.content,fragmentTrailer);
                    transaction.commit();
                    return true;
                case R.id.fragment_Customerservice:
                    Intent intent = new Intent(MainActivity.this,FragmentCustomservices.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment=new FragmentIntroduce();
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.content,fragment);
        transaction.commit();
        ActivityCompat.requestPermissions(this,new String[]{"android.permission.READ_EXTERNAL_STORAGE"},1);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void handlesudi(View view){
        Intent intent=new Intent(this,ten_one_sudi.class);
        startActivityForResult(intent,1);
    }
    public void handleduanqiao(View view){
        Intent intent=new Intent(this,ten_two_duanqiao.class);
        startActivityForResult(intent,1);
    }
    public void handlepinglake(View view){
        Intent intent=new Intent(this,ten_three_pinglake.class);
        startActivityForResult(intent,1);
    }
    public void handleliu(View view){
        Intent intent=new Intent(this,ten_four_liu.class);
        startActivityForResult(intent,1);
    }
    public void handlefeng(View view){
        Intent intent=new Intent(this,ten_five_feng.class);
        startActivityForResult(intent,1);
    }
    public void handlesantan(View view){
        Intent intent=new Intent(this,ten_six_santan.class);
        startActivityForResult(intent,1);
    }
    public void handlewatchfish(View view){
        Intent intent=new Intent(this,ten_seven_watchfish.class);
        startActivityForResult(intent,1);
    }
    public void handlesorth(View view){
        Intent intent=new Intent(this,ten_eight_sorth.class);
        startActivityForResult(intent,1);
    }
    public void handleleifeng(View view){
        Intent intent=new Intent(this,ten_nine_leifeng.class);
        startActivityForResult(intent,1);
    }
    public void handlehehua(View view){
        Intent intent=new Intent(this,ten_ten_hehua.class);
        startActivityForResult(intent,1);
    }
}
