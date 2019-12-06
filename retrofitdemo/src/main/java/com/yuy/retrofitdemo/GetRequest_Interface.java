package com.yuy.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * from : yuy
 * Date : 2019/12/5
 * Description :创建 用于描述网络请求(Get) 的接口
 * Retrofit将 Http请求 抽象成 Java接口：采用 注解 描述网络请求参数 和配置网络请求参数
 * VersionYM :
 */
public interface GetRequest_Interface {

    // @GET注解的作用:采用Get方法发送网络请求
    @GET("ajax.php?a=fy&f=auto&t=auto&w=你好")
    Call<Translation> getCall();
    //完整url: http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=你好
    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
    /// 注解里传入 网络请求 的部分URL地址
    //    // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
    //    // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
    //    // getCall()是接受网络请求数据的方法
    //}


    //1、 注解里传入 网络请求 的部分URL地址； getCall()是接受网络请求数据的方法

    //2、注解类型：
    //网络请求方式： @GET @POST @PUT @DELETE @PATH @HEAD @OPTIONS @HTTP
    //标记类： @FormUrlEncode  @Multipart @Streaming
    //网络请求参数： @Head @URL @Body @Path @Field @PartMap @Query @QueryMap


}
