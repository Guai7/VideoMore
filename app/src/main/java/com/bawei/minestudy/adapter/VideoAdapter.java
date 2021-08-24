package com.bawei.minestudy.adapter;

import android.widget.ImageView;

import com.bawei.minestudy.R;
import com.bawei.minestudy.entity.VideoEntity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * MineStudy
 * name: VideoAdapter
 * time: 2021/8/20 10:52.
 * author: 王益德
 * Describe:
 */
public class VideoAdapter extends BaseMultiItemQuickAdapter<VideoEntity.DataBean, BaseViewHolder> {

    public VideoAdapter(@Nullable List<VideoEntity.DataBean> data) {
        super(data);
        addItemType(0, R.layout.fa_item_layout);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, VideoEntity.DataBean dataBean) {
        baseViewHolder.setText(R.id.fa_item_username_text,dataBean.getAuthname());

        Glide.with(getContext()).load(dataBean.getHeadpath()).circleCrop().into((ImageView) baseViewHolder.getView(R.id.fa_item_head_img));
        Glide.with(getContext()).load(dataBean.getVideomainimg()).placeholder(R.drawable.ic_launcher_background).into((ImageView) baseViewHolder.getView(R.id.fa_item_img));
    }
}
