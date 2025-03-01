package com.example.homequest;

public class HomeS {
    private String name;
    private String contact;
    private int imageResId;

    public HomeS(String name, String contact, int imageResId) {
        this.name = name;
        this.contact = contact;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public int getImageResId() {
        return imageResId;
    }
}
