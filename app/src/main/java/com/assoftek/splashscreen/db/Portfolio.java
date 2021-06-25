package com.assoftek.splashscreen.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "portfolio_data")
public class Portfolio {

    @PrimaryKey
    @NonNull
    public String table_name;

    @ColumnInfo
    public String asset_class;

    @ColumnInfo
    public Double allocation_percentage;

    @ColumnInfo
    public Double allocation;

    @ColumnInfo
    public Double weighted_return_percentage;

    @ColumnInfo
    public Double weighted_return;

    @ColumnInfo
    public Double sd;

    @ColumnInfo
    public Double weighted_sd;

    public Portfolio(@NonNull String table_name, String asset_class, Double allocation_percentage, Double allocation,
                     Double weighted_return_percentage, Double weighted_return, Double sd, Double weighted_sd) {
        this.table_name = table_name;
        this.asset_class = asset_class;
        this.allocation_percentage = allocation_percentage;
        this.allocation = allocation;
        this.weighted_return_percentage = weighted_return_percentage;
        this.weighted_return = weighted_return;
        this.sd = sd;
        this.weighted_sd = weighted_sd;
    }

}
