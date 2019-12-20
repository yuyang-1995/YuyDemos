package com.yuy.viewpagerindicatordemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/20.
 */
public class MyAppLife implements Application.ActivityLifecycleCallbacks {
    private int startCount;
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

        startCount++;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        startCount--;
//        System.out.println("==============" + startCount);
        if (startCount == 0) {
            System.out.println("==============退出App==============");
        }

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    public int getStartCount(){
        return startCount;
    }

}
