package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * Created by aung on 12/3/17.
 */

@Entity(tableName = "FavouriteAction",
        foreignKeys = {@ForeignKey(entity = NewsVO.class,
                parentColumns = "newsId",
                childColumns = "news_id"),
                @ForeignKey(entity = ActedUserVO.class,
                        parentColumns = "actedUserId",
                        childColumns = "user_id")})
public class FavoriteActionVO {

    @NotNull
    @PrimaryKey
    @SerializedName("favorite-id")
    private String favoriteId;

    @SerializedName("favorite-date")
    private String favoriteDate;

    @ColumnInfo(name = "news_id")
    private String newsId;

    @ColumnInfo(name = "user_id")
    private String userId;

    @Ignore
    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    @NotNull
    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(@NotNull String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public void setFavoriteDate(String favoriteDate) {
        this.favoriteDate = favoriteDate;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }
}
