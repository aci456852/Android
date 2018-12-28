package com.example.administrator.highlevel;

/**
 * Created by lzy on 2018/12/25.
 */

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends Service {

    public MediaPlayer mediaPlayer;

    class MyBinder extends Binder {
        public void plays() {
            play();
        }

        public void pauses() {
            pause();
        }

        public void replays() {
            replay();
        }

        public int getCurrentPosition() {
            return getCurrentProgress();
        }

        public int getMusicWidth() {
            return getMusicLength();
        }
    }

    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.two);
    }

    //播放音乐
    public void play() {
        try {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, R.raw.two);
            }
            mediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//设置重复播放
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
    }

    //暂停音乐
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else if (mediaPlayer != null && (!mediaPlayer.isPlaying())) {
            mediaPlayer.start();
        }
    }

    //重新播放音乐
    public void replay() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            try {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(this, R.raw.two);
                }
                mediaPlayer.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
        }
    }

    //获取资源文件的长度
    public int getMusicLength() {
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    //获取当前进度
    public int getCurrentProgress() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                return mediaPlayer.getCurrentPosition();
            } else if (!mediaPlayer.isPlaying()) {
                return mediaPlayer.getCurrentPosition();
            }
        }
        return 0;
    }

    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}

