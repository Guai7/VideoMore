package com.bawei.minestudy.mvp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MineStudy
 * name: HttpLoginUtils
 * time: 2021/8/24 13:37.
 * author: 王益德
 * Describe:
 */
public class HttpLoginUtils {
    private Retrofit retrofit;
    private static HttpLoginUtils httpUtils;

    private HttpLoginUtils() {
    }

    public static HttpLoginUtils getInstance() {
        if (httpUtils==null){
            httpUtils = new HttpLoginUtils();
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
