package com.example.up_mobil;

public class MaskFeelings {

    private int id;
    private String title;
    private int position;
    private String image;

    public MaskFeelings(int id, String title, int position, String image) {
        this.id = id;
        this.title = title;
        this.position = position;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
