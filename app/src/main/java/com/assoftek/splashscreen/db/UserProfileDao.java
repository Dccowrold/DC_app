package com.assoftek.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserProfileDao {

    @Insert
    void insertProfile(UserProfile profile);

}
