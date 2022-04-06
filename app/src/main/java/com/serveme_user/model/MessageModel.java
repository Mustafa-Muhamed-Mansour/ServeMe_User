package com.serveme_user.model;

public class MessageModel
{

    private String randomKey;
    private String id;
    private String message;
//    private String employeeImage;
//    private String employeeName;
//    private String image;
//    private String video;

    public MessageModel()
    {
    }

    public MessageModel(String randomKey, String id, String message)
    {
        this.randomKey = randomKey;
        this.id = id;
        this.message = message;
    }

    //    public MessageModel(String randomKey, String id, String message, String employeeImage, String employeeName)
//    {
//        this.randomKey = randomKey;
//        this.id = id;
//        this.message = message;
//        this.employeeImage = employeeImage;
//        this.employeeName = employeeName;
//    }

    public String getRandomKey() {
        return randomKey;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

//    public String getEmployeeImage() {
//        return employeeImage;
//    }

//    public String getEmployeeName() {
//        return employeeName;
//    }

    //    public MessageModel(String randomKeyMessage, String message)
//    {
//        this.message = message;
//    }
//
//    public MessageModel(String image)
//    {
//        this.image = image;
//    }
//
//
//    public String getRandomKey() {
//        return randomKey;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public String getVideo() {
//        return video;
//    }
}
