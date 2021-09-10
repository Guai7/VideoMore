package com.bawei.minestudy.mvp.contract;

import com.bawei.minestudy.entity.LogAndRegEntity;
import com.bawei.minestudy.entity.VideoEntity;
import com.bawei.mybase.model.IModel;
import com.bawei.mybase.view.IView;

import java.util.List;

import io.reactivex.Observer;

/**
 * MineStudy
 * name: IContract
 * time: 2021/8/19 14:51.
 * author: 王益德
 * Describe:
 */
public interface IContract{
    interface ILoginModel extends IModel{
        void login(String username,String pwd,Observer<LogAndRegEntity> observer);
    }
    interface ILoginView extends IView{
        void showLogin(LogAndRegEntity data);
    }

    interface IRegisterModel extends IModel{
        void register(String username,String pwd,Observer<LogAndRegEntity> observer);
    }

    interface IRegisterView extends IView{
        void showRegister(LogAndRegEntity data);
    }

    interface IVideoModel extends IModel{
        void getVideoData(int page,Observer<VideoEntity> observer);
    }

    interface IVideoView extends IView{
        void showVideo(List<VideoEntity.DataBean> list);
    }
}
