package com.yuy.yaoyiyaopage;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.util.TimeUnit;

/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/18.
 */
public class ShakeUtils implements SensorEventListener {

    private SensorManager mSensorManager;
    private OnShakeListener mOnShakeListener;
    private float last_x, last_y, last_z;
    private static final float SHAKE_THRESHOLD = 20f;

    private long lastSnakeTime;
    private int lastSnakeCount;//已触发摇动次数
    private int maxSnakeCount=2;//摇动次数达到此值认为成功，因为初次摇动判断时置为0消耗掉一次事件，实际需要摇动次数为maxSnakeCount+1
    private  boolean canSnake=true;//是否能够进行摇动回调。这个用于第一次领取用户未处理的情况下，不触发第二次回调


    public ShakeUtils(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void setCanSnake(boolean canSnake) {
        this.canSnake = canSnake;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!canSnake) {
            return;
        }
        int sensorType = event.sensor.getType();
        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            //这里可以调节摇一摇的灵敏度
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            float xdiff = Math.abs(x - last_x);
            float ydiff = Math.abs(y - last_y);
            float zdiff = Math.abs(z - last_z);
            float diff = xdiff + ydiff + zdiff;  //摇动幅度

            if (diff > SHAKE_THRESHOLD){
            long  nowTime = System.currentTimeMillis();
            if (nowTime-lastSnakeTime<1000){
                //摇动间隔小于1000认为是连续摇动不处理
                lastSnakeCount++;
                if (lastSnakeCount==maxSnakeCount){
                    //摇动成功，只触发一次
                    if (mOnShakeListener!=null){
                        mOnShakeListener.onShake();
                    }
                }
            }else{
                //否则认为是初次摇动
                lastSnakeCount=0;
            }
            lastSnakeTime = System.currentTimeMillis();
            }

             last_x = x;
             last_y = y;
             last_z = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void setOnShakeListener(OnShakeListener mOnShakeListener) {
        this.mOnShakeListener = mOnShakeListener;
    }

    public interface OnShakeListener{
        void onShake();

    }

    public void onResume() {
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        mSensorManager.unregisterListener(this);
    }
}
