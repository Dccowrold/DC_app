package com.assoftek.splashscreen.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_data")
public class User {

    @PrimaryKey
    @NonNull
    public String email;

    public String password;

    public String username;

}
