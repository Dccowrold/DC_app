package com.assoftek.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface DetailsDao {

    @Insert
    void insertUser(Details details);

    @Delete
    void delete(Details details);

}
