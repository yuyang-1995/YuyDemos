package com.yuy.imageloaddemo.imageloader;


import io.reactivex.Observable;

/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/30.
 */
public class RequestCreator {


    private MemoryCacheObservable memoryCacheObservable;
    private DiskCacheObservable diskCacheObservable;
    private NetWorkCacheObservable netWorkCacheObservable;

    public RequestCreator() {
        memoryCacheObservable = new MemoryCacheObservable();
        diskCacheObservable = new DiskCacheObservable();
        netWorkCacheObservable = new NetWorkCacheObservable();

    }

    public Observable<Image> getImageFromMemory(String url) {

        return memoryCacheObservable.getImage(url);
    }

    public Observable<Image> getImageFromDisk(String url) {

        return diskCacheObservable.getImage(url);
    }

    public Observable<Image> getImageFromNetWork(String url) {
        return netWorkCacheObservable.getImage(url);
    }

}
