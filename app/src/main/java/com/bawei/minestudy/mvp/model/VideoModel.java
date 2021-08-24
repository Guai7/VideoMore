package com.bawei.minestudy.mvp.model;

import com.bawei.minestudy.entity.VideoEntity;
import com.bawei.minestudy.mvp.Api;
import com.bawei.minestudy.mvp.HttpUtils;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.mybase.model.BaseModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * MineStudy
 * name: VideoModel
 * time: 2021/8/20 11:00.
 * author: 王益德
 * Describe:
 */
public class VideoModel extends BaseModel implements IContract.IVideoModel {
    @Override
    public void getVideoData(int page,Observer<VideoEntity> observer) {
        HttpUtils.getInstance()
                .getRetrofit(Api.VideoBaseUrl)
                .create(Api.class)
                .requestVideoData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
