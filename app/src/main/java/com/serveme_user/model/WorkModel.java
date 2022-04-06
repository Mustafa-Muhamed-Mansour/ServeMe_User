package com.serveme_user.model;

public class WorkModel
{

    private String randomKey;
    private String image;
    private String title;


    public WorkModel()
    {
    }

    public WorkModel(String randomKey, String image, String title)
    {
        this.randomKey = randomKey;
        this.image = image;
        this.title = title;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
