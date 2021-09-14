package com.bawei.mybase.mvp.presenter;

import com.bawei.mybase.mvp.model.IModel;
import com.bawei.mybase.mvp.view.IView;

import javax.inject.Inject;

public class BasePresenter<V extends IView,M extends IModel> implements IPresenter{

    protected V view;
    protected M model;

    @Inject
    public BasePresenter(V view, M model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void destroy() {
        if (model!=null){
            model.destroy();
            model = null;
        }
    }
}
