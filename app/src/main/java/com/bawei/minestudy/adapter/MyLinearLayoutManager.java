package com.bawei.minestudy.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * MineStudy
 * name: MyLinearLayoutManager
 * time: 2021/8/20 15:00.
 * author: 王益德
 * Describe:
 */
public class MyLinearLayoutManager extends LinearLayoutManager{

    private PagerSnapHelper pagerSnapHelper;


    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(view);
    }
}
