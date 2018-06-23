package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;

import java.util.List;

/**
 * Created by E5 on 6/17/2018.
 */

public interface NewsListView extends BaseView {
    void displayNewsList(List<NewsVO> newList);

    void launchNewsDetail(String newsId);
}
