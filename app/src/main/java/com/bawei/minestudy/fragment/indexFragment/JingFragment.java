package com.bawei.minestudy.fragment.indexFragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.minestudy.R;
import com.bawei.minestudy.adapter.MyLinearLayoutManager;
import com.bawei.minestudy.adapter.VideoJingAdapter;
import com.bawei.minestudy.entity.VideoEntity;
import com.bawei.minestudy.mvp.contract.IContract;
import com.bawei.minestudy.mvp.model.VideoModel;
import com.bawei.minestudy.mvp.presenter.VideoPresenter;
import com.bawei.minestudy.myView.MyVideo;
import com.bawei.mybase.view.BaseFragment;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * MineStudy
 * name: JingFragment
 * time: 2021/8/20 0:54.
 * author: 王益德
 * Describe:
 */
public class JingFragment extends BaseFragment<VideoPresenter> implements IContract.IVideoView{
    private RecyclerView jingRv;

    @Override
    public void initView() {
        presenter = new VideoPresenter(this, new VideoModel());
        jingRv = findViewById(R.id.jing_rv);
        jingRv.setLayoutManager(new MyLinearLayoutManager(getActivity()));
//        jingRv.setAdapter();

        jingRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ( newState==RecyclerView.SCROLL_STATE_IDLE){
                    RecyclerView.LayoutManager layoutManager = jingRv.getLayoutManager();
                    assert layoutManager != null;
                    MyVideo player = Objects.requireNonNull(layoutManager.getChildAt(0)).findViewById(R.id.jing_player);
                    player.startPlayLogic();
                }
            }
        });
    }

    @Override
    public void initData() {
        presenter.getVideo(6);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_index_jing;
    }

    @Override
    public void showVideo(List<VideoEntity.DataBean> list) {
        VideoJingAdapter videoJingAdapter = new VideoJingAdapter(list);
        jingRv.setAdapter(videoJingAdapter);
    }
}
