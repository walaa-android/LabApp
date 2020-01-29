package com.example.labmedixapplication.model;

public class LocationModel {

    private String userLocation;
    private String labLocation;
    private float userLat;
    private float userLong;
    private float labLat;
    private float labLong;

    public LocationModel(String userLocation) {
        this.userLocation = userLocation;
    }

    public LocationModel() {
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

    public float getUserLat() {
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
    }
}
