package com.yuy.alarmdemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    public AlarmService() {
    }

    public static String ACTION_ALARM = "action_alarm";
    private Handler mHanler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Toast.makeText(AlarmService.this, "闹钟来啦", Toast.LENGTH_SHORT).show();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = null;
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ad", true);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).
                setContentTitle("This is content title").
                setContentText("This is content text").
                setWhen(System.currentTimeMillis()).
                setSmallIcon(R.mipmap.ic_launcher).
                setPriority(NotificationCompat.PRIORITY_DEFAULT).
                setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).
                setContentIntent(pendingIntent).
                setAutoCancel(true);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        startForeground(1, notification);
    }

    String CHANNEL_ONE_ID = "com.primedu.cn";
    String CHANNEL_ONE_NAME = "Channel One";

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private NotificationChannel createNotificationChannel() {
//        NotificationChannel notificationChannel = null;
//        notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
//                CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
//        notificationChannel.enableLights(true);
//        notificationChannel.setLightColor(Color.RED);
//        notificationChannel.setShowBadge(true);
//        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//        return notificationChannel;
//    }

    String CHANNEL_ID = "com.example.recyclerviewtest.N1";
    String CHANNEL_NAME = "TEST";
    //    	NotificationChannel notificationChannel = null;
    //    	if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
    //    	    notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
    //    	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    //     	   notificationManager.createNotificationChannel(notificationChannel);
    //   		}
    //        Intent intent = new Intent(this, MainActivity.class);
    //        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent, 0);
    //
    //        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID).
    //                setContentTitle("This is content title").
    //                setContentText("This is content text").
    //                setWhen(System.currentTimeMillis()).
    //                setSmallIcon(R.mipmap.ic_launcher).
    //                setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)).
    //                setContentIntent(pendingIntent).build();
    //        startForeground(1, notification);
    //————————————————
    //版权声明：本文为CSDN博主「6号楼下的大懒喵」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    //原文链接：https://blog.csdn.net/CV_Jason/article/details/99731979

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }



    private static final String TAG = "MyService";
    private static final String ID = "channel_1";
    private static final String NAME = "前台服务";
    private int notificationId = 1;
}
