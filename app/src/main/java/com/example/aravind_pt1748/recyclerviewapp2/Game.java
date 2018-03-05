package com.example.aravind_pt1748.recyclerviewapp2;

import android.widget.TextView;

public class Game {

    private String title;
    private String genre;
    private String releaseYear;
    private int imageId;

    public Game(String title,String genre,String releaseYear,int imageId){
        this.title=title;
        this.genre=genre;
        this.releaseYear=releaseYear;
        this.imageId=imageId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
