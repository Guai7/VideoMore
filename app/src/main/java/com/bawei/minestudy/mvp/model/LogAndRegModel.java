package com.bawei.minestudy.mvp.model;

import com.bawei.minestudy.entity.LogAndRegEntity;
import com.bawei.minestudy.mvp.Api;
import com.bawei.minestudy.mvp.HttpLoginUtils;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.mybase.mvp.model.BaseModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * MineStudy
 * name: LoginModel
 * time: 2021/8/19 14:54.
 * author: 王益德
 * Describe:
 */
public class LogAndRegModel extends BaseModel implements IContract.ILoginModel, IContract.IRegisterModel{

    @Override
    public void login(String username,String pwd,Observer<LogAndRegEntity> observer) {
        HttpLoginUtils.getInstance()
                .getRetrofit(Api.BASE_URL_LOGIN)
                .create(Api.class)
                .requestLogin(username,pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void register(String username, String pwd, Observer<LogAndRegEntity> observer) {
        HttpLoginUtils.getInstance()
                .getRetrofit(Api.BASE_URL_LOGIN)
                .create(Api.class)
                .requestRegister(username,pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
