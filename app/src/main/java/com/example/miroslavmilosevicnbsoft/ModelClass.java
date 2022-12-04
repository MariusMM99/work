package com.example.miroslavmilosevicnbsoft;

public class ModelClass {
    private String id;
    private String brand;
    private String name;
    private String img;
    private String price;

    public ModelClass(String id, String brand, String name, String img, String price) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public ModelClass()
    {
        // empty constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
