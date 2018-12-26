package com.example.administrator.highlevel;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import static android.content.Context.BIND_AUTO_CREATE;


/**
 * Created by Administrator on 2017/12/8.
 */

public class FragmentTrailer extends AppCompatActivity{

    private  Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trailer);
        start=(Button)findViewById(R.id.button_play1);
        stop=(Button)findViewById(R.id.button_stop1);
        start.setOnClickListener(startlis);
        stop.setOnClickListener(stoplis);
    }

    private void requestPower() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }
    private OnClickListener startlis=new OnClickListener(){

        @Override
        public void onClick(View v) {
            //启动服务
            startService(new Intent(FragmentTrailer.this, MusicService.class));
        }

    };
    private OnClickListener stoplis=new OnClickListener(){

        @Override
        public void onClick(View v) {
            //停止服务
            stopService(new Intent(FragmentTrailer.this,MusicService.class));
        }

    };

}
