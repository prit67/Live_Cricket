package com.cricket.crickais.crickcode;

public class mod3 {

    private String head, idd, news, newshead, newssub,desc, newsimg;

    public mod3() {

    }

    public mod3(String head, String idd, String news, String newshead, String newssub, String desc, String newsimg) {
        this.head = head;
        this.idd = idd;
        this.news = news;
        this.newshead = newshead;
        this.newssub = newssub;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNewsimg() {
        return newsimg;
    }

    public void setNewsimg(String newsimg) {
        this.newsimg = newsimg;
    }
}