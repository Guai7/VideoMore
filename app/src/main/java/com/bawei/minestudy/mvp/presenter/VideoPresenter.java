package com.bawei.minestudy.mvp.presenter;

import com.bawei.minestudy.entity.VideoEntity;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.mybase.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * MineStudy
 * name: VideoPresenter
 * time: 2021/8/20 11:05.
 * author: 王益德
 * Describe:
 */
public class VideoPresenter extends BasePresenter<IContract.IVideoView, IContract.IVideoModel>{

    private Disposable disposable;

    @Inject
    public VideoPresenter(IContract.IVideoView view, IContract.IVideoModel model) {
        super(view, model);
    }

    public void getVideo(int page){
        model.getVideoData(page,new Observer<VideoEntity>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NotNull VideoEntity videoEntity) {
                view.showVideo(videoEntity.getData());
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
