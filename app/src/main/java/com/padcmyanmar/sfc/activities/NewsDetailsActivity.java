package com.padcmyanmar.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.NewsImagesPagerAdapter;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.presenters.NewsDetailPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aung on 11/11/17.
 */

public class NewsDetailsActivity extends BaseActivity implements NewsDetailView {

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.tv_news_details)
    TextView tvnewsDetail;

    private NewsDetailPresenter mNewsDetailPresenter;

    private static final String IE_NEWS_ID = "IE_NEWS_ID";

    public static Intent newIntent(Context context, String newsId) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra("IE_NEWS_ID", newsId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this, this);

        NewsImagesPagerAdapter newsImagesPagerAdapter = new NewsImagesPagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagesPagerAdapter);

        mNewsDetailPresenter = new NewsDetailPresenter(this);
        mNewsDetailPresenter.onCreat();

        String newsId = getIntent().getStringExtra(IE_NEWS_ID);
        mNewsDetailPresenter.onFinishUiSetupComponent(newsId);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mNewsDetailPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mNewsDetailPresenter.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNewsDetailPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNewsDetailPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNewsDetailPresenter.onDestroy();
    }

    @Override
    public void displayNewsDetail(NewsVO newsVO) {

        tvnewsDetail.setText(newsVO.getDetails());
    }

    @Override
    public void displayErrorMessage(String message) {

    }
}
