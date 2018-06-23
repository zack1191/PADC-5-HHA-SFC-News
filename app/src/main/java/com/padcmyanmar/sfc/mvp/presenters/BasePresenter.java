package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.mvp.views.BaseView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by E5 on 6/17/2018.
 */

public abstract class BasePresenter<T extends BaseView> {
    protected T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
    }

    public  void onCreat(){

    }

    public  void onStart(){

    }

    public  void onStop(){

    }

    public  void onResume(){

    }

    public  void onPause(){

    }

    public  void onDestroy(){

    }


}
