package com.example.labmedixapplication.model;

import android.net.Uri;

 public   class UserModel {


    private String userId;
    private String fullName;
    private String mImageUrlPohto;
    private  String userEmail;
    private String phoneNo;
    private String userLocation;
    private String labLocation;
    private  String gender;
    private  String birth;
   // private float userLat;
    //private float userLong;
   // private float labLat;
   // private float labLong;


     public UserModel() {
     }

     public UserModel(String userLocation) {
        this.fullName = userLocation;
    }

   public UserModel(String fullName, String userEmail, String gender , String birth , String mImageUrlPohto ) {
        this.fullName = fullName;
        this.userEmail = userEmail;
        this.gender = gender;
        this.birth = birth;
        this.mImageUrlPohto=mImageUrlPohto;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getLabLocation() {
        return labLocation;
    }

    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
    }

 /*   public float getUserLat() {
        return userLat;
    }

    public void setUserLat(float userLat) {
        this.userLat = userLat;
    }

    public float getUserLong() {
        return userLong;
    }

    public void setUserLong(float userLong) {
        this.userLong = userLong;
    }

    public float getLabLat() {
        return labLat;
    }

    public void setLabLat(float labLat) {
        this.labLat = labLat;
    }

    public float getLabLong() {
        return labLong;
    }

    public void setLabLong(float labLong) {
        this.labLong = labLong;
    }*/

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getmImageUrlPohto() {
        return mImageUrlPohto;
    }

    public void setmImageUrlPohto(String  mImageUrlPohto) {
        this.mImageUrlPohto = mImageUrlPohto;
    }
}
