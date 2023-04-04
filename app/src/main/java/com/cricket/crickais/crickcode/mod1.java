package com.cricket.crickais.crickcode;

public class mod1 {

    private String head, idd, news, newshead, newssub, newsimg;

    public mod1() {

    }

    public mod1(String head, String idd, String news, String newshead, String newssub, String newsimg) {
        this.head = head;
        this.idd = idd;
        this.news = news;
        this.newshead = newshead;
        this.newssub = newssub;
        this.newsimg = newsimg;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getNewshead() {
        return newshead;
    }

    public void setNewshead(String newshead) {
        this.newshead = newshead;
    }

    public String getNewssub() {
        return newssub;
    }

    public void setNewssub(String newssub) {
        this.newssub = newssub;
    }

    public String getNewsimg() {
        return newsimg;
    }

    public void setNewsimg(String newsimg) {
        this.newsimg = newsimg;
    }
}