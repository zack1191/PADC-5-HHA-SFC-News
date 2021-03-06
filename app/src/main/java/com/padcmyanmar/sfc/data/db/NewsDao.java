package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by E5 on 6/6/2018.
 */
@Dao
public interface NewsDao extends BaseDao<NewsVO>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNew(NewsVO newsVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertNews(NewsVO... newsVOS);

    @Query("SELECT * FROM News")
    List<NewsVO> getAllNews();

    @Query("DELETE FROM News")
    void deleteAll();

}
