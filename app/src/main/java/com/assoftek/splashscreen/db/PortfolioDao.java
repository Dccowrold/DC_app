package com.assoftek.splashscreen.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PortfolioDao {

    @Insert
    void insertPortfolio(Portfolio portfolio);

    @Delete
    void delete(Portfolio portfolio);

    @Query("SELECT * FROM portfolio_data WHERE table_name LIKE '%Portfolio ' || :type || ' ' || :number || '%'")
    Portfolio findPortfolioTable(String type, String number);

}
