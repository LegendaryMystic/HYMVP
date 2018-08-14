package com.example.dl.hymvp.http;

import android.util.Log;

import com.example.dl.hymvp.App;
import com.example.dl.hymvp.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Incremental change is better than ambitious failure.
 *
 * @author : <a href="http://mysticcoder.coding.me">MysticCoder</a>
 * @date : 2018/3/15
 * @desc :
 */
public class ApiEngine {

    private static final long DEFAULT_TIMEOUT = 10;


    private volatile static ApiEngine apiEngine;
    private Retrofit retrofit;

    private ApiEngine() {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor  = new HttpLoggingInterceptor((message)-> {

            try {
                String text = URLDecoder.decode(message, "utf-8");
                Log.e("OKHttp-----", text);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.e("OKHttp-----", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(App.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);


        //        addInterceptor() 添加应用拦截器
//● 不需要担心中间过程的响应,如重定向和重试.
//● 总是只调用一次,即使HTTP响应是从缓存中获取.
//● 观察应用程序的初衷. 不关心OkHttp注入的头信息如: If-None-Match.
//● 允许短路而不调用 Chain.proceed(),即中止调用.
//● 允许重试,使 Chain.proceed()调用多次.


//                addNetworkInterceptor() 添加网络拦截器
//● 能够操作中间过程的响应,如重定向和重试.
//● 当网络短路而返回缓存响应时不被调用.
//● 只观察在网络上传输的数据.
//● 携带请求来访问连接.

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
//                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                //然后将下面的GsonConverterFactory.create()替换成我们自定义的ResponseConverterFactory.create()
                .addConverterFactory(ResponseConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }

}
