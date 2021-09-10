package com.bawei.minestudy.dagger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.minestudy.mvp.model.VideoModel;

import dagger.Module;
import dagger.Provides;

/**
 * MineStudy
 * name: DaggerModule
 * time: 2021/9/1 16:11.
 * author: 王益德
 * Describe:
 */
@Module
public class VideoImageModule{
    private IContract.IVideoView view;

    public VideoImageModule(IContract.IVideoView view) {
        this.view = view;
    }

    @Provides
    public IContract.IVideoView getView() {
        return view;
    }

    @Provides
    public IContract.IVideoModel getModel() {
        return new VideoModel();
    }
}
