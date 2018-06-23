package com.padcmyanmar.sfc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.viewholders.NewUiNewsViewHolder;
import com.padcmyanmar.sfc.viewholders.NewsViewHolder;

/**
 * Created by E5 on 6/20/2018.
 */

public class NewUiNewsAdapter extends BaseRecyclerAdapter<NewUiNewsViewHolder, NewsVO> {

    private NewsItemDelegate mNewsItemDelegate;


    public NewUiNewsAdapter(Context context, NewsItemDelegate newsItemDelegate) {
        super(context);
        mNewsItemDelegate = newsItemDelegate;
    }

    @NonNull
    @Override
    public NewUiNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newUiView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_new_ui_news, parent, false);
        return new NewUiNewsViewHolder(newUiView, mNewsItemDelegate);
    }

}
