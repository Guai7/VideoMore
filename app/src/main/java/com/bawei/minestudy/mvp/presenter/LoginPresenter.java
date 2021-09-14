package com.bawei.minestudy.mvp.presenter;

import com.bawei.minestudy.entity.LogAndRegEntity;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.mybase.mvp.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * MineStudy
 * name: LogAndRegPresenter
 * time: 2021/8/19 15:03.
 * author: 王益德
 * Describe:
 */
public class LoginPresenter extends BasePresenter<IContract.ILoginView, IContract.ILoginModel>{

    private Disposable disposable;

    public LoginPresenter(IContract.ILoginView view, IContract.ILoginModel model) {
        super(view, model);
    }

    public void login(String username,String pwd){

        if (username == null) {
            username = "";
        }
        if (pwd==null){
            pwd = "";
        }

        model.login(username, pwd, new Observer<LogAndRegEntity>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NotNull LogAndRegEntity logAndRegEntity) {
                view.showLogin(logAndRegEntity);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                disposable.dispose();
            }

            @Override
            public void onComplete() {
                disposable.dispose();
            }
        });
    }
}
