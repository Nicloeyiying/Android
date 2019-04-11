package com.example.myapplication.bean;

public class Product {
    private int id;
    private String image;
    private String title;
    private String price;
    public Product(){

    }
    public Product(String img, String title, String price){
        this.image = img;
        this.title = title;
        this.price = price;
    }
    public int getId(){
        return id;
    }
    public void setId(){
        this.id = id;
    }
    public String getImage(){
        return image;
    }
    public void setImg(){
        this.image = image;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(){
        this.title = title;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(){
        this.price = price;
    }

}
