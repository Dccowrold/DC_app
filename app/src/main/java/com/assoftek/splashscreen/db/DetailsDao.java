package com.assoftek.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DetailsDao {

    @Insert
    void insertUser(Details details);

    @Delete
    void delete(Details details);

    @Query("SELECT * FROM details_data WHERE email = :emailID")
    Details findUserFromEmail(String emailID);

}
