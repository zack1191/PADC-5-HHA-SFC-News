package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.views.NewsDetailView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by E5 on 6/17/2018.
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    private NewsVO newsVO;

    public NewsDetailPresenter(NewsDetailView mView) {
        super(mView);

    }


    public void onFinishUiSetupComponent(String newsId) {
        NewsVO newsVO = NewsModel.getInstance().getNewsById(newsId);
        mView.displayNewsDetail(newsVO);
    }
}
