package com.serveme_user.model;

public class ServiceModel
{

    private String randomKey;
    private String idJob;
    private String image;
    private String name;

    public ServiceModel()
    {
    }

    public ServiceModel(String randomKey, String idJob, String image, String name)
    {
        this.randomKey = randomKey;
        this.idJob = idJob;
        this.image = image;
        this.name = name;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public String getIdJob() {
        return idJob;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
