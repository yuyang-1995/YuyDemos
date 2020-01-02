package com.yuy.imageloaddemo.imageloader;

import android.content.Context;
import android.widget.ImageView;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.functions.Function;


/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/30.
 */
public class RxImageLoader {

    private volatile static RxImageLoader singleton;
    private String mUrl;
    private ImageView mImageView;
    private RequestCreator requestCreator;


    private RxImageLoader() {

        requestCreator = new RequestCreator();

    }

    public static RxImageLoader with(Context context) {

        if (singleton == null) {
            synchronized (RxImageLoader.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public RxImageLoader load(String url) {
        this.mUrl = url;
        return singleton;
    }

    public void into(ImageView imageView) {
        this.mImageView = imageView;

        Observable.concat(requestCreator.getImageFromMemory(mUrl), requestCreator.getImageFromDisk(mUrl)
                , requestCreator.getImageFromNetWork(mUrl))
                .map(new Function<Image, Boolean>() {

                    @Override
                    public Boolean apply(Image image) throws Exception {
                        return null;
                    }
                });


    }

    public static class Builder {

        private final Context context;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context;

        }

        public RxImageLoader build() {
            return new RxImageLoader();

        }

    }
}
