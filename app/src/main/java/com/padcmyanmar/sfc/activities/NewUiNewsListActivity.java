package com.padcmyanmar.sfc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.NewUiNewsAdapter;
import com.padcmyanmar.sfc.components.EmptyViewPod;
import com.padcmyanmar.sfc.components.SmartRecyclerView;
import com.padcmyanmar.sfc.data.models.NewsModel;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.mvp.presenters.NewsListPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by E5 on 6/20/2018.
 */

public class NewUiNewsListActivity extends BaseActivity implements NewsListView {

    @BindView(R.id.rv_ui_news)
    SmartRecyclerView rvUiNews;

    private NewUiNewsAdapter mNewUiNewsAdapter;
    private NewsListPresenter mNewsListPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ui_news_list);
        ButterKnife.bind(this, this);

        mNewsListPresenter = new NewsListPresenter(this);
        mNewsListPresenter.onCreat();

        Toolbar toolbar = findViewById(R.id.toolbar_news_ui);
        setSupportActionBar(toolbar);

        rvUiNews.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false));
        mNewUiNewsAdapter = new NewUiNewsAdapter(getApplicationContext(), mNewsListPresenter);
        rvUiNews.setAdapter(mNewUiNewsAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mNewsListPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mNewsListPresenter.onStop();
    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
//        mNewUiNewsAdapter.appendNewData(event.getLoadNews());
//    }

    @Override
    public void displayErrorMessage(String message) {
        Snackbar.make(rvUiNews, message, Snackbar.LENGTH_INDEFINITE).show();

    }

    @Override
    public void displayNewsList(List<NewsVO> newList) {
        mNewUiNewsAdapter.appendNewData(newList);
    }

    @Override
    public void launchNewsDetail(String newsId) {
        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext(), newsId);
        startActivity(intent);
    }
}
