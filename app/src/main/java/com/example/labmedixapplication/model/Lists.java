package com.example.labmedixapplication.model;


public class Lists {

    private int image;
    private String type;
    private String no;
    private int next;

    public Lists(String type, String no) {
        this.type = type;
        this.no = no;
    }

    public Lists(int next) {
        this.next = next;
    }

    public Lists(String type) {
        this.type = type;
    }

    public Lists(int image, String type, String no, int next) {
        this.image = image;
        this.type = type;
        this.no = no;
        this.next = next;
    }

    public Lists(int image, String type, int next) {
        this.image = image;
        this.type = type;
        this.next = next;
    }

    public Lists() {
    }




  /*  public Lists(String type, int next) {
        this.type = type;
        this.next = next;
    }

    public Lists(int image, String type, int next) {
        this.image = image;
        this.type = type;
        this.next = next;
    }*/

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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
