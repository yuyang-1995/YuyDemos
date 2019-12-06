package com.yuy.rxjavademo.request;

import com.yuy.rxjavademo.bean.Translation3;
import com.yuy.rxjavademo.bean.Translation4;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * from : yuy
 * Date : 2019/12/6
 * Description :
 * VersionYM :
 */
public interface GetRequest_Interface3 {

    // 网络请求1
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation3> getCall();

    // 网络请求2
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20china")
    Observable<Translation4> getCall_2();

    // 注解里传入 网络请求 的部分URL地址
    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    // 采用Observable<...>接口
    // getCall()是接受网络请求数据的方法
}
