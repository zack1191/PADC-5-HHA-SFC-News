package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "ActedUser")
public class ActedUserVO {

    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "actedUserId")
    private String userId;

    @ColumnInfo(name = "name")
    @SerializedName("user-name")
    private String userName;

    @ColumnInfo(name = "profile")
    @SerializedName("profile-image")
    private String profileImage;

    @NotNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
