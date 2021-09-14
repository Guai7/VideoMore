package com.bawei.minestudy.mvp.presenter;

import com.bawei.minestudy.entity.LogAndRegEntity;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.mybase.mvp.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * MineStudy
 * name: RegisterPresenter
 * time: 2021/8/19 15:06.
 * author: 王益德
 * Describe:
 */
public class RegisterPresenter extends BasePresenter<IContract.IRegisterView, IContract.IRegisterModel>{

    private Disposable disposable;

    public RegisterPresenter(IContract.IRegisterView view, IContract.IRegisterModel model) {
        super(view, model);
    }

    public void register(String username,String pwd){
        model.register(username, pwd, new Observer<LogAndRegEntity>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NotNull LogAndRegEntity logAndRegEntity) {
                view.showRegister(logAndRegEntity);
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
