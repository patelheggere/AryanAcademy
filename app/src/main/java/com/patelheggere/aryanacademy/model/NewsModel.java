package com.patelheggere.aryanacademy.model;

public class NewsModel {
    private String key;
    private String news;

    public NewsModel() {
    }

    public NewsModel(String key, String news) {
        this.key = key;
        this.news = news;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
