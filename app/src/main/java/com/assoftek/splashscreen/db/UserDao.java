package com.assoftek.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User users);

    @Delete
    void delete(User user);

}
