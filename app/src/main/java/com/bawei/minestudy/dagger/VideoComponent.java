package com.bawei.minestudy.dagger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bawei.minestudy.fragment.indexFragment.FaFragment;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.minestudy.mvp.model.VideoModel;

import dagger.Component;
import dagger.Provides;

/**
 * MineStudy
 * name: DaggerComponent
 * time: 2021/9/1 16:11.
 * author: 王益德
 * Describe:
 */
@Component(modules = VideoImageModule.class)
public interface VideoComponent{

    void inject(FaFragment faFragment);
//    void inject(AppCompatActivity activity);

}
