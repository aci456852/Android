package com.example.administrator.highlevel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * Created by Administrator on 2017/12/8.
 */

public class FragmentTrailer extends Fragment{

    private  Button start,stop;
    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trailer);
        start=(Button)findViewById(R.id.button_play1);
        stop=(Button)findViewById(R.id.button_stop1);
        start.setOnClickListener(startlis);
        stop.setOnClickListener(stoplis);
    }*/
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trailer,container,false);
        start=(Button)view.findViewById(R.id.button_play1);
        stop=(Button)view.findViewById(R.id.button_stop1);
        start.setOnClickListener(startlis);
        stop.setOnClickListener(stoplis);

        return view;
    }
    /*private void requestPower() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }*/
    private OnClickListener startlis=new OnClickListener(){

        @Override
        public void onClick(View v) {
            //启动服务
            Intent serviceIntend = new Intent(getActivity(),MusicService.class);
            getActivity().startService(serviceIntend);
            //startService(new Intent(FragmentTrailer.this, MusicService.class));
        }

    };
    private OnClickListener stoplis=new OnClickListener(){

        @Override
        public void onClick(View v) {
            //停止服务
            Intent serviceIntend2 = new Intent(getActivity(),MusicService.class);
            getActivity().stopService(serviceIntend2);
            //stopService(new Intent(FragmentTrailer.this,MusicService.class));
        }

    };

}
