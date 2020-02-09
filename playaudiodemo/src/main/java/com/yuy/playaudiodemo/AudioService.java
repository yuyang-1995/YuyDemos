package com.yuy.playaudiodemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

public class AudioService extends Service {
    public AudioService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mPlayer1 = new MyIjkMediaPlayer(this);
//        try {
//            mPlayer1.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mPlayer1.init();
//        mPlayer1.setPath("rtmp://58.200.131.2:1935/livetv/hunantv");
//        Log.d("AudioService", "Service Start");
//        Log.d("palyer", (mPlayer1 == null) + "");
//        mPlayer1.start();
        MainActivity.mPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }


}
