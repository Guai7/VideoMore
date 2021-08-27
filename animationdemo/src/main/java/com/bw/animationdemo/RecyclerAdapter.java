package com.bw.animationdemo;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * MineStudy
 * name: RecyclerAdapter
 * time: 2021/8/26 22:00.
 * author: 王益德
 * Describe:
 */
public class RecyclerAdapter extends BaseMultiItemQuickAdapter<GiftEntity.DataBean, BaseViewHolder> {
    public RecyclerAdapter(@Nullable List<GiftEntity.DataBean> data) {
        super(data);
        addItemType(0,R.layout.item_gift_view);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GiftEntity.DataBean dataBean) {
        
        baseViewHolder.setText(R.id.item_gift_text,dataBean.getName());

        Glide.with(App.context).load(dataBean.getPreviewImg()).into((ImageView) baseViewHolder.getView(R.id.item_gift_img));
    }
}
