package com.yuy.viewpagerindicatordemo;

import android.app.Application;

/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/20.
 */
public class MyApplication extends Application {

    private static MyAppLife myAppLife;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppLife = new MyAppLife();
        registerActivityLifecycleCallbacks(myAppLife);
    }


}
