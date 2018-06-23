package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.data.vo.NewsVO;

/**
 * Created by E5 on 6/17/2018.
 */

public interface NewsDetailView extends BaseView{
    void displayNewsDetail(NewsVO newsVO);
}
