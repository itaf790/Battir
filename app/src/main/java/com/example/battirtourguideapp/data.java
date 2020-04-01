package com.example.battirtourguideapp;

public class data {
    String name;
    private String Description;
    private String ImageUrl;
    private String ImageUrl2;

    public data(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }



    public String getImageUrl2() {
        return ImageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        ImageUrl2 = imageUrl2;
    }

}
