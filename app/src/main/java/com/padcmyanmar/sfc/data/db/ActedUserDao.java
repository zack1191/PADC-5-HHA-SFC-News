package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by E5 on 6/9/2018.
 */
@Dao
public interface ActedUserDao extends BaseDao<ActedUserVO> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertActedUser(ActedUserVO data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertActedUsers(ActedUserVO... actedUserVOS);

    @Query("SELECT * FROM ActedUser")
    List<ActedUserVO> getActedUser();

    @Query("DELETE FROM ActedUser")
    void deleteAll();
}
