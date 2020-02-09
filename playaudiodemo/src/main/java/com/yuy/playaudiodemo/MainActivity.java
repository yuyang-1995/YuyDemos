package com.yuy.playaudiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    static MyIjkMediaPlayer mPlayer;

    SurfaceView mSurfaceView;
    TextView mView, mAudio;

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
    private boolean isAudio;
    private boolean AudioModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = new MyIjkMediaPlayer(this);
        mSurfaceView = findViewById(R.id.m_sview);
        mSurfaceView.getHolder().addCallback(surfaceCallback);
        mPlayer.init(mSurfaceView);
        mPlayer.setPath("rtmp://58.200.131.2:1935/livetv/hunantv");
        mView = findViewById(R.id.tv);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasStarted) {
                            mPlayer.start();
                            hasStarted = true;
                            mView.setText("暂停(看电视中..)");
                        } else {
                            mPlayer.pause();
                            mView.setText("点击看电视");
                            hasStarted = false;
                        }
                }
        });

        mAudio = findViewById(R.id.audio);
        mAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAudio) {
                    isAudio = true;
                    mView.setText("点击看电视");
                    mAudio.setText("暂停(听电视中..)");
                    mPlayer.pause();
                    Intent intent = new Intent(MainActivity.this, AudioService.class);
                    startService(intent);
                }else {
                    isAudio = false;
                    mPlayer.pause();
                    mAudio.setText("点击听电视");
                    Intent intent = new Intent(MainActivity.this, AudioService.class);
                    stopService(intent);
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private boolean isFirst = true;
    @Override
    protected void onResume() {
        super.onResume();
        if (!isAudio) {
            mPlayer.start();
            if (!isFirst) {
                mView.setText("暂停(看电视中..)");
            }
        }else {
            mView.setText("点击看电视");
        }
        isFirst = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isAudio){
            mPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
    }


}
