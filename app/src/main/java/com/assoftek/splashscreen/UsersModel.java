package com.assoftek.splashscreen;

public class UsersModel {


    String userId;
    String userName,email,phoneNumber, countryCode, password,state, pinCode, gender, dob;
    String  graduationCourse, graduationUniversity, graduationMajors, graduationDate;
    String  postGraduationCourse, postGraduationUniversity, postGraduationMajors, postGraduationDate;
    String  aadharCard, panCard;

    public UsersModel( String userName, String email, String phoneNumber,
                      String countryCode, String password, String state, String pinCode, String gender,
                      String dob) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.password = password;
        this.state = state;
        this.pinCode = pinCode;
        this.gender = gender;
        this.dob = dob;
    }

    public UsersModel(){} // empty for firebase

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public String getGraduationCourse() {
        return graduationCourse;
    }

    public void setGraduationCourse(String graduationCourse) {
        this.graduationCourse = graduationCourse;
    }

    public String getGraduationUniversity() {
        return graduationUniversity;
    }

    public void setGraduationUniversity(String graduationUniversity) {
        this.graduationUniversity = graduationUniversity;
    }

    public String getGraduationMajors() {
        return graduationMajors;
    }

    public void setGraduationMajors(String graduationMajors) {
        this.graduationMajors = graduationMajors;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getPostGraduationCourse() {
        return postGraduationCourse;
    }

    public void setPostGraduationCourse(String postGraduationCourse) {
        this.postGraduationCourse = postGraduationCourse;
    }

    public String getPostGraduationUniversity() {
        return postGraduationUniversity;
    }

    public void setPostGraduationUniversity(String postGraduationUniversity) {
        this.postGraduationUniversity = postGraduationUniversity;
    }

    public String getPostGraduationMajors() {
        return postGraduationMajors;
    }

    public void setPostGraduationMajors(String postGraduationMajors) {
        this.postGraduationMajors = postGraduationMajors;
    }

    public String getPostGraduationDate() {
        return postGraduationDate;
    }

    public void setPostGraduationDate(String postGraduationDate) {
        this.postGraduationDate = postGraduationDate;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }
}


