package com.bawei.minestudy.fragment.indexFragment;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bawei.minestudy.R;
import com.bawei.minestudy.activity.DetailsActivity;
import com.bawei.minestudy.adapter.VideoAdapter;
import com.bawei.minestudy.dagger.DaggerVideoComponent;
import com.bawei.minestudy.dagger.VideoImageModule;
import com.bawei.minestudy.entity.VideoEntity;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.minestudy.mvp.presenter.VideoPresenter;
import com.bawei.mybase.mvp.view.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * MineStudy
 * name: FaFragment
 * time: 2021/8/20 0:54.
 * author: 王益德
 * Describe:
 */
public class FaFragment extends BaseFragment<VideoPresenter> implements IContract.IVideoView, OnRefreshListener, OnLoadMoreListener, OnItemClickListener{
    private RecyclerView faRv;

    private int page = 1;
    private SmartRefreshLayout faRefresh;
    private VideoAdapter videoAdapter;
    private boolean isRefresh;

    private List<VideoEntity.DataBean> adapterData;

    @Override
    public void initView() {
        DaggerVideoComponent.builder().videoImageModule(new VideoImageModule(this)).build().inject(this);

        faRv = (RecyclerView) findViewById(R.id.fa_rv);
        faRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        faRefresh = findViewById(R.id.fa_refresh);
        faRefresh.setOnLoadMoreListener(this);
        faRefresh.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        presenter.getVideo(page);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_index_fa;
    }

    @Override
    public void showVideo(List<VideoEntity.DataBean> list) {
        if (videoAdapter == null){
            videoAdapter = new VideoAdapter(list);
            videoAdapter.setOnItemClickListener(this);
            faRv.setAdapter(videoAdapter);
        }else {

            faRefresh.finishRefresh();
            faRefresh.finishLoadMore();

            if (isRefresh){
                videoAdapter.getData().clear();
            }



            videoAdapter.getData().addAll(list);
            videoAdapter.notifyDataSetChanged();
        }

        adapterData = videoAdapter.getData();

    }

    @Override
    public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
        page++;
        isRefresh = true;
        presenter.getVideo(page);
    }

    @Override
    public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
        page++;
        isRefresh = false;
        presenter.getVideo(page);
    }

    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("video",adapterData.get(position));
        startActivity(intent);
    }
}
