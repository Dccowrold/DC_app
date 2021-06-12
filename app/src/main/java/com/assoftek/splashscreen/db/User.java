package com.assoftek.splashscreen.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_data")
public class User {

    @PrimaryKey
    @NonNull
    public String email;

    @ColumnInfo
    public String password;

    @ColumnInfo
    public String username;

}
