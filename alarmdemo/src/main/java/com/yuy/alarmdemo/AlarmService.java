package com.yuy.alarmdemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
            setForeground();
        } else {
            Notification notification = createNotification();

            startForeground(notificationId, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHanler.sendEmptyMessage(1);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                Notification notification = new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.ic_launcher_foreground)
//                        .setTicker("您的签到红包待领取，立即领取吧")
//                        .setContentTitle("每天签到赚"  + "喔~").setContentText("赶紧点我去签到吧~")
//                        .setAutoCancel(true)
//                        .setDefaults(Notification.DEFAULT_ALL)
////                        .setContentIntent(contentIntent)
//                        .build();

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setTicker("您的签到红包待领取，立即领取吧")
//                .setContentTitle("每天签到赚" + "喔~")
//                .setContentText("赶紧点我去签到吧~")
//                .setDefaults(Notification.DEFAULT_ALL);
        Notification notification = createNotification();
        //这里的通知栏发送通知参数id的值必须唯一，若为相同id则会覆盖
        nm.notify(2819141, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    private Notification createNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("听电视")
//                .setStyle(new android.support.v4.media.app.NotificationCompat.DecoratedMediaCustomViewStyle())
//                .setCustomContentView(getRemoteView())
                .setContentText("已开启听电视")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
                .setContentIntent(pendingIntent)
                .build();
        return notification;
    }

    @TargetApi(26)
    private void setForeground() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_LOW);
        manager.createNotificationChannel(channel);
        Notification notification = createNotification();
        startForeground(notificationId, notification);
    }

    private static final String TAG = "MyService";
    private static final String ID = "channel_1";
    private static final String NAME = "前台服务";
    private int notificationId = 1;
}
