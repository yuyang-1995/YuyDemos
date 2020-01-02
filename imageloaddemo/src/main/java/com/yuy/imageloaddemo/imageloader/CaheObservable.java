package com.yuy.imageloaddemo.imageloader;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Class:
 * Other:
 * Create by yuy on  2019/12/30.
 */
public abstract class CaheObservable {

    public  Observable<Image> getImage(final String url){

        return Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(ObservableEmitter<Image> emitter) throws Exception {

                if (!emitter.isDisposed()) {
                    Image image = getDataFromCache(url);
                    emitter.onNext(image);
                    emitter.onComplete();
                }


            }
        });
    }

    public abstract Image getDataFromCache(String url);

    public abstract Image putDataToCache(Image image);


}
