package com.example.up_mobil;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MaskQuotes implements Parcelable {

    private int id;
    private String title;
    private String image;
    private String description;

    public MaskQuotes(int id, String title, String image, String description) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    protected MaskQuotes(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        description = in.readString();
    }

    public static final Creator<MaskQuotes> CREATOR = new Creator<MaskQuotes>() {
        @Override
        public MaskQuotes createFromParcel(Parcel in) {
            return new MaskQuotes(in);
        }

        @Override
        public MaskQuotes[] newArray(int size) {
            return new MaskQuotes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(description);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
