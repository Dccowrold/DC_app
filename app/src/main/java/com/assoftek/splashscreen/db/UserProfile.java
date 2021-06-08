package com.assoftek.splashscreen.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Profile")
public class UserProfile {

    @PrimaryKey
    @NonNull
    public String name;

    public String state;

    public String pincode;

    public String Dob;

    public String Phone_no;

}
