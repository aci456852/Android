package com.example.administrator.highlevel;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Administrator on 2017/12/8.
 */

public class FragmentTrailer extends Fragment implements View.OnClickListener{


    /*public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trailer,container,false);
        start=(Button)view.findViewById(R.id.button_play1);
        stop=(Button)view.findViewById(R.id.button_stop1);
        start.setOnClickListener(startlis);
        stop.setOnClickListener(stoplis);

        return view;
    }*/
    private ImageView img;
    private RotateAnimation animation;
    private Intent intent;
    private myConn conn;
    MusicService.MyBinder binder;
    private SeekBar mSeekBar;
    private Thread mThread;
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case 100:
                    int currentPosition=(Integer) msg.obj;
                    mSeekBar.setProgress(currentPosition);
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trailer,container,false);
        view.findViewById(R.id.bt_play).setOnClickListener(this);
        view.findViewById(R.id.bt_pause).setOnClickListener(this);
        view.findViewById(R.id.bt_replay).setOnClickListener(this);
        img= (ImageView) view.findViewById(R.id.img_music);
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mSeekBar=(SeekBar)view.findViewById(R.id.seekBar);
        conn=new myConn();
        intent=new Intent(getActivity(),MusicService.class);
        getActivity().bindService(intent, conn,BIND_AUTO_CREATE);
        requestPower();
        return view;
    }

    private void requestPower() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    private void initSeekBar(){
        int musicWidth=binder.getMusicWidth();
        mSeekBar.setMax(musicWidth);
    }
    //更新进度条
    private void UpdateProgress() {
        mThread = new Thread(){
            public void run() {
                while (!interrupted()) {
                    int currentPosition = binder.getCurrentPosition();
                    Message message = Message.obtain();
                    message.obj = currentPosition;
                    message.what = 100;
                    handler.sendMessage(message);
                }
            };
        };
        mThread.start();
    }

    private class myConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (MusicService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    private void indatae() {
        animation.setDuration(10000);//设定转一圈的时间
        animation.setRepeatCount(Animation.INFINITE);//设定无限循环
        animation.setRepeatMode(Animation.RESTART);//
        img.startAnimation(animation);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_play:
                binder.plays();
                initSeekBar();
                UpdateProgress();
                indatae();//开始旋转
                break;
            case R.id.bt_pause:
                binder.pauses();
                animation.cancel();//暂停旋转
                break;
            case R.id.bt_replay:
                binder.replays();
                indatae();//开始旋转
                break;
            default:
                break;
        }
    }

    public void onDestroy(){
        if(mThread!=null){
            if(!mThread.isInterrupted())
                mThread.interrupt();
        }
        getActivity().unbindService(conn);
        super.onDestroy();
    }

}
