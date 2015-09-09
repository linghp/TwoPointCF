package com.peoit.twopointcf.base;

public  class BasePresenter<V extends IBaseView_Response> {
    public V mView;

    public BasePresenter(V view) {
        initView(view);
        //initModel();
    }

    public void initView(V view) {
        this.mView = view;
    }

    //public abstract void initModel();
}
