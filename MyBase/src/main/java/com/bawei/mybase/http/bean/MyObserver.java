package com.bawei.mybase.http.bean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * +----------------------------------------------------------------------
 * | 项   目: MineStudy
 * +----------------------------------------------------------------------
 * | 包   名: com.bawei.mybase.http.bean
 * +----------------------------------------------------------------------
 * | 类   名: MyObserver
 * +----------------------------------------------------------------------
 * | 时　　间: 2021/9/12 19:07
 * +----------------------------------------------------------------------
 * | 代码创建: 王益德
 * +----------------------------------------------------------------------
 * | 版本信息: V1.0.0
 * +----------------------------------------------------------------------
 * | 功能描述:
 * +----------------------------------------------------------------------
 **/
public abstract class MyObserver<T> implements Observer<DataBean<T>>,IObserver {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(DataBean<T> tDataBean) {
        success(tDataBean.getData());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void fail(ErrorBean errorBean) {

    }
}
