package com.example.recyclerview;

import java.io.Serializable;

public class Article implements Serializable {
    private String title;
    private String content;
    private int imgCover;
    private int views;

    public Article(String title, String content, int imgCover) {
        this.title = title;
        this.content = content;
        this.imgCover = imgCover;
        this.views = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgCover() {
        return imgCover;
    }

    public void setImgCover(int imgCover) {
        this.imgCover = imgCover;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void incrementViews() {
        this.views++;
    }
}
