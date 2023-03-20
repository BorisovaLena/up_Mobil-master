package com.example.up_mobil;

import java.io.File;

public class MaskPhoto {

    public int id;
    public String path;
    public File image;
    public String date;

    public MaskPhoto(int id, String path, File image, String date) {
        this.id = id;
        this.path = path;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public File getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setImageProfile(File image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
