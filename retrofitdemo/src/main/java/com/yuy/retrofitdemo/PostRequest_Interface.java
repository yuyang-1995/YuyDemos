package com.yuy.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * from : yuy
 * Date : 2019/12/5
 * Description :
 * VersionYM :
 */
public interface PostRequest_Interface {

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<Translation1> getCall(@Field("i") String t1, @Field("d") String t2, @Field("r") String t3);
    //采用@Post表示Post方法进行请求（传入部分url地址）
    // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
    // 需要配合@Field 向服务器提交需要的字段

    //2、注解类型：
    //网络请求方式： @GET @POST @PUT @DELETE @PATH @HEAD @OPTIONS @HTTP
    //标记类： @FormUrlEncode  @Multipart @Streaming
    //网络请求参数： @Head @URL @Body @Path @Field @PartMap @Query @QueryMap



}
