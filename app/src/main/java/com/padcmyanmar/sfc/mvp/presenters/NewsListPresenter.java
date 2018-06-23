package com.padcmyanmar.sfc.mvp.presenters;

import android.support.design.widget.Snackbar;

import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.mvp.views.NewsListView;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by E5 on 6/17/2018.
 */

public class NewsListPresenter extends BasePresenter<NewsListView> implements NewsItemDelegate {


    private PublishSubject<GetNewsResponse> mSubject;
    private NewsVO mNewsVO;

    public NewsListPresenter(NewsListView view) {
        super(view);

    }

    @Override
    public void onCreat() {
        super.onCreat();
        NewsModel.getInstance().startLoadingMMNews();
//        NewsModel.getInstance().startLoadingMMNews(mSubject);
//
//        mSubject = PublishSubject.create();
//        mSubject.subscribe(new io.reactivex.Observer<GetNewsResponse>()
//
//        {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(GetNewsResponse getNewsResponse) {
//                mNewsListView.displayNewsList(getNewsResponse.getNewsList());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        if (event.getLoadNews() == null) {
            mView.displayErrorMessage("Empty data");
        } else {
            mView.displayNewsList(event.getLoadNews());
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorLoaded(RestApiEvents.ErrorInvokingAPIEvent event) {
        mView.displayErrorMessage(event.getErrorMsg());
    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatistics() {

    }

    @Override
    public void onTapNews(NewsVO newsVo) {
        mView.launchNewsDetail(newsVo.getNewsId());
    }


}
