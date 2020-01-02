package com.yuy.imageloaddemo.imageloader;

import android.graphics.Bitmap;

/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/30.
 */
public class Image {
    private String url;
    private Bitmap bitmap;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
