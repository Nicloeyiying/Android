package com.example.myapplication;

public class Pro {
    private String num;
    private String status;
    private int img;
    private String title;
    private String price;
    private String pro_time;
    private String time;
    private int imgButton;
    public Pro(String num, String status, int img, String title, String price, String pro_time, String time, int imgButton){
        this.num =num;
        this.status = status;
        this.img = img;
        this.title = title;
        this.price = price;
        this.pro_time = pro_time;
        this.time = time;
        this.imgButton = imgButton;
    }
    public String getNum(){
        return num;
    }
    public void setNum(String num){
        this.num = num;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public int getImg(){
        return img;
    }
    public void setImg(int img){
        this.img = img;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPro_time(){
        return pro_time;
    }
    public void setPro_time(String pro_time){
        this.pro_time = pro_time;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
    public int getImgButton(){
        return imgButton;
    }
    public void setImgButton(int imgButton){
        this.imgButton = imgButton;
    }
}
