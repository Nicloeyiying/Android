package com.example.circleoffriends;

public class Status {
    private String name;
    private String content;
    private String time;
    private int img;
    private int img1;
    private int img2;
    private int img3;

    public Status(String name, String content, String time, int img, int img1, int img2, int img3) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.img = img;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public int getImg3() {
        return img3;
    }

    public void setImg3(int img3) {
        this.img3 = img3;
    }
}
