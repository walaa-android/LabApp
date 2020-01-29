package com.example.labmedixapplication.model;

public class ListCondition {
    private String type;
    private String describtion;
    private  int image;


    public ListCondition() {
    }

    public ListCondition(String type, String describtion) {
        this.type = type;
        this.describtion = describtion;
    }

    public ListCondition(String type, String describtion, int image) {
        this.type = type;
        this.describtion = describtion;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}
