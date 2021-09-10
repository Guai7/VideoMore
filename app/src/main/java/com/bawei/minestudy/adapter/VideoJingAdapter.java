package com.bawei.minestudy.adapter;

import android.view.View;

import com.bawei.minestudy.R;
import com.bawei.minestudy.entity.VideoEntity;
import com.bawei.minestudy.myView.MyVideo;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * MineStudy
 * name: VideoJingAdapter
 * time: 2021/8/20 15:03.
 * author: 王益德
 * Describe:
 */
public class VideoJingAdapter extends BaseMultiItemQuickAdapter<VideoEntity.DataBean, BaseViewHolder>{

    private int i = 0;

    public VideoJingAdapter(@Nullable List<VideoEntity.DataBean> data) {
        super(data);
        addItemType(0, R.layout.jing_item_layout);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, VideoEntity.DataBean dataBean) {
        MyVideo player = baseViewHolder.getView(R.id.jing_player);
        player.setUp(dataBean.getVideopath(),true,dataBean.getAuthname());

        if (i==0){
            player.startPlayLogic();
            i++;
        }
    }
}
