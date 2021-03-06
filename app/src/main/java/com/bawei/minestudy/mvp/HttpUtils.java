package com.bawei.minestudy.mvp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MineStudy
 * name: HttpUtils
 * time: 2021/8/19 14:55.
 * author: ็็ๅพท
 * Describe:
 */
public class HttpUtils{
    private Retrofit retrofit;
    private static HttpUtils httpUtils;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (httpUtils==null){
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    public synchronized Retrofit getRetrofit(String baseUrl) {
        if (retrofit == null) {
            synchronized (Retrofit.class){
                if (retrofit==null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .client(new OkHttpClient.Builder()
                                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                    .build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
