package com.serveme_user.model;

public class SliderModel
{

    private int imageSlider;
    private String nameSlider;

    public SliderModel()
    {
    }

    public SliderModel(int imageSlider, String nameSlider)
    {
        this.imageSlider = imageSlider;
        this.nameSlider = nameSlider;
    }

    public int getImageSlider() {
        return imageSlider;
    }

    public String getNameSlider() {
        return nameSlider;
    }
}
