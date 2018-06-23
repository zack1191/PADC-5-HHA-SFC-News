package com.padcmyanmar.sfc.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;

import butterknife.BindView;

/**
 * Created by E5 on 6/20/2018.
 */

public class NewUiNewsViewHolder extends BaseViewHolder<NewsVO> {

    @BindView(R.id.iv_ui_news_image)
    ImageView ivNewsImage;

    @BindView(R.id.tv_ui_publication_title)
    TextView tvPublicationTitle;

    private NewsItemDelegate mNewsItemDelegate;
    private NewsVO mNewsVO;

    public NewUiNewsViewHolder(View itemView, NewsItemDelegate mNewsItemDelegate) {
        super(itemView);
        this.mNewsItemDelegate = mNewsItemDelegate;
    }

    @Override
    public void setData(NewsVO data) {
        mNewsVO = data;
        if (data.getImages() != null) {
            ivNewsImage.setVisibility(View.VISIBLE);
            Glide.with(ivNewsImage.getContext())
                    .load(data.getImages().get(0))
                    .into(ivNewsImage);
        }

        tvPublicationTitle.setText(data.getPublication().getTitle());
    }

    @Override
    public void onClick(View view) {
        mNewsItemDelegate.onTapNews(mNewsVO);
    }
}
