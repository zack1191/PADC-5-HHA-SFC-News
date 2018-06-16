package com.padcmyanmar.sfc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

/**
 * Created by E5 on 6/15/2018.
 */
@Dao
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(T... data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T data);
}
