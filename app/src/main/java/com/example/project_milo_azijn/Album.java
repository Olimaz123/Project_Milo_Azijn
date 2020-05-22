package com.example.project_milo_azijn;

import java.io.Serializable;

public class Album implements Serializable {
    private int id;
    private String artist;
    private String album;
    private String type;
    private String year;

    public Album (String artist, String album, String type, String year){
        this.artist = artist;
        this.album = album;
        this.type = type;
        this.year = year;
    }

    public Album (int id, String artist, String album, String type, String year){
        this.id = id;
        this.artist = artist;
        this.album = album;
        this.type = type;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
