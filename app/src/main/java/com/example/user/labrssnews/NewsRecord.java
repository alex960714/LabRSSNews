package com.example.user.labrssnews;

import java.util.Date;

public class NewsRecord {

    private String title;
    private String link;
    private String description;

    public NewsRecord(String title, String link, String description, Date publishedDate) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

}
