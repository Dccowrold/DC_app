package com.assoftek.splashscreen;

public class UsersModel {

    String userName;
    String phoneNumber;
    String countryCode;
    String password;
    String state;
    String homeTown;
    String gender;
    String dob;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public UsersModel(){}

    public UsersModel(String userName, String phoneNumber, String countryCode, String password, String state, String homeTown, String gender, String dob) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.password = password;
        this.state = state;
        this.homeTown = homeTown;
        this.gender = gender;
        this.dob = dob;
    }
}


