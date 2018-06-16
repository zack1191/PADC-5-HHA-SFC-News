package com.padcmyanmar.sfc.data.models;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.sfc.SFCNewsApp;
import com.padcmyanmar.sfc.data.db.AppDatabase;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.MMNewsAPI;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel {

    private static NewsModel objInstance;

    private AppDatabase mAppDatabase;


    private int mmNewsPageIndex = 1;
    private List<NewsVO> mNews;
    private MMNewsAPI theAPI;
    private List<PublicationVO> mPublication;
    private PublishSubject<List<NewsVO>> mSubject;

    private NewsModel() {

        mNews = new ArrayList<>();

//        EventBus.getDefault().register(this);
        initAPI();

    }

    public void startLoadingMMNewes() {
//        MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex);
        Observable<GetNewsResponse> newsResponseObservable = theAPI.loadMMNews(mmNewsPageIndex, AppConstants.ACCESS_TOKEN);
        newsResponseObservable
                .subscribeOn(Schedulers.io())
                .map(new Function<GetNewsResponse, List<NewsVO>>() {
                    @Override
                    public List<NewsVO> apply(GetNewsResponse getNewsResponse) throws Exception {
                        return getNewsResponse.getNewsList();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsVO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSubject.onSubscribe(d);
                    }

                    @Override
                    public void onNext(List<NewsVO> newsVOS) {
                        mSubject.onNext(newsVOS);
                        mNews.addAll(newsVOS);
                        mmNewsPageIndex++;

                    }

                    @Override
                    public void onError(Throwable e) {
                        mSubject.onError(e);
                        Log.e(SFCNewsApp.LOG_TAG, "onError : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mSubject.onComplete();
                        Log.d(SFCNewsApp.LOG_TAG, "onComplete : ");
                    }
                });
    }

    public static NewsModel getInstance() {
        if (objInstance == null) {
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    private void initAPI() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(MMNewsAPI.class);
        mSubject = PublishSubject.create();

    }

    public void initialDatabase(Context context) {
        mAppDatabase = AppDatabase.getNewsDatabase(context);
    }


//
//    public LiveData<List<NewsVO>> getMMNews() {
//        return mAppDatabase.newsDao().getAllNews();
//    }

//
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void onDataLoadedEvent(RestApiEvents.NewsDataLoadedEvent event) {
//        mNews.addAll(event.getLoadNews());
//        mmNewsPageIndex = event.getLoadedPageIndex() + 1;
//
//        mAppDatabase.publicationDao().deleteAll();
//        mAppDatabase.newsDao().deleteAll();
//        mAppDatabase.actedUserDao().deleteAll();
//        mAppDatabase.commentDao().deleteAll();
//        mAppDatabase.favouriteDao().deleteAll();
//
//        for (NewsVO news : event.getLoadNews()) {
//
//            long[] publication = mAppDatabase.publicationDao().insertAll(mPublication.toArray(new PublicationVO[0]));
//            Log.d(SFCNewsApp.LOG_TAG, "Publication : " + publication.length);
//
//            long[] insertNews = mAppDatabase.newsDao().insertAll(mNews.toArray(new NewsVO[0]));
//            Log.d(SFCNewsApp.LOG_TAG, "News : " + insertNews.length);
//        }
//
//
//    }

    public PublishSubject<List<NewsVO>> getNewsFromNetwork() {
        return mSubject;
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onNewsLoadedEvent(RestApiEvents.NewsDataLoadedEvent event) {
//        mAppDatabase.newsDao().deleteAll();
//        long[] insertpublication = mAppDatabase.publicationDao().insertPublications(event.getLoadNews().toArray(new PublicationVO[0]));
//        long[] insertedIds = mAppDatabase.newsDao()
//                .insertNews(event.getLoadNews().toArray(new NewsVO[0]));
//        Log.d(SFCNewsApp.LOG_TAG, "Total inserted count : " + insertedIds.length);
//    }
}
