package com.padcmyanmar.sfc.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;

import java.util.List;

/**
 * Created by E5 on 6/10/2018.
 */
@Dao
public interface CommentDao extends BaseDao<CommentActionVO> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertComment(CommentActionVO commentActionVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertComments(CommentActionVO... commentActionVOS);

    @Query("SELECT * FROM CommentAction")
    List<CommentActionVO> getCommentAction();

    @Query("DELETE FROM CommentAction")
    void deleteAll();
}
