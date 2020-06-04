package com.mhmdawad.newsapp.models;

public class Country {

    private String smallName;
    private String name;
    private String image;

    public Country(String smallName, String name, String image) {
        this.smallName = smallName;
        this.name = name;
        this.image = image;
    }

    public String getSmallName() {
        return smallName;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
