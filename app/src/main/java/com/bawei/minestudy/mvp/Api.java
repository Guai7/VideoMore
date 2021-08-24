package com.bawei.minestudy.mvp;

import com.bawei.minestudy.entity.LogAndRegEntity;
import com.bawei.minestudy.entity.VideoEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * MineStudy
 * name: Api
 * time: 2021/8/19 1:29.
 * author: 王益德
 * Describe:
 */
public interface Api {

    String BASE_URL_LOGIN = "http://www.fenghongzhang.com:7777";

    /**
     * 注册接口
     * @param userName 账号
     * @param userPwd 密码
     * @return 被观察者
     */
    @POST("/register/new")
    @FormUrlEncoded
    Observable<LogAndRegEntity> requestRegister(@Field("userName")String userName,@Field("userPwd")String userPwd);

    /**
     * 登录接口
     * @param userName 账号
     * @param userPwd 密码
     * @return 被观察者
     */
    @POST("/login/in")
    @FormUrlEncoded
    Observable<LogAndRegEntity> requestLogin(@Field("userName")String userName,@Field("userPwd")String userPwd);


    String VideoBaseUrl = "http://39.98.153.96:8088/zytestapi/video/";

    /**
     * 请求视频数据
     * @return 被观察者
     */
    @GET("findVideos?pageSize=10")
    Observable<VideoEntity> requestVideoData(@Query("currentPage")int page);
}
