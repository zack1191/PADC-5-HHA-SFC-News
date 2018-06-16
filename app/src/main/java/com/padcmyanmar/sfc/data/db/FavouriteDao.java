package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by E5 on 6/10/2018.
 */
@Dao
public interface FavouriteDao extends BaseDao<FavoriteActionVO> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFavourite(FavoriteActionVO favoriteActionVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertFavourites(FavoriteActionVO... favoriteActionVOS);

    @Query("SELECT * FROM FavouriteAction")
    List<FavoriteActionVO> getFavourite();

    @Query("DELETE FROM FavouriteAction")
    void deleteAll();
}
