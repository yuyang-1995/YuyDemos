package com.yuy.playaudiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    MyIjkMediaPlayer mPlayer;

    SurfaceView mSurfaceView;
    TextView mView;

    boolean hasStarted = false;

    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            try {
                mPlayer.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (mSurfaceView != null) {
                mSurfaceView.getHolder().removeCallback(surfaceCallback);
                mSurfaceView = null;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = new MyIjkMediaPlayer(this);

        mSurfaceView = findViewById(R.id.m_sview);
        mSurfaceView.getHolder().addCallback(surfaceCallback);
        mPlayer.init(mSurfaceView);
//        mPlayer.setPath("rtmp://220.248.34.75:1935/live/camera_2");
        mPlayer.setPath("rtmp://58.200.131.2:1935/livetv/hunantv");

        mView = findViewById(R.id.tv);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("MyIjkMediaPlayer", "onClick: hasStarted-"+hasStarted);
                if (!hasStarted) {
                    mPlayer.start();
                    hasStarted = true;
                    mView.setText("暂停");
                } else {
                    mPlayer.pause();
                    mView.setText("开始");
                    hasStarted = false;
                }

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
    }


}
