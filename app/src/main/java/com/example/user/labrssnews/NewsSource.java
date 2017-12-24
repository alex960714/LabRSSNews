package com.example.user.labrssnews;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsSource {
    private static final String BASE_PATH = "http://news.yandex.ru/";
    public static final String ALL = BASE_PATH;
    public static final String TOP = BASE_PATH + "index.html";
    public static final String POLITICS = BASE_PATH + "politics.html";
    public static final String BUSINESS = BASE_PATH + "business.html";
    public static final String SPORT = BASE_PATH + "sport.html";

    public static void getContent(String pageNumber) {
        NewsSource content = new NewsSource();
        switch (pageNumber) {
            case "ALL":
                content.downloadContent(ALL);
            case "TOP":
                content.downloadContent(TOP);
            case "POLITICS":
                content.downloadContent(POLITICS);
            case "BUSINESS":
                content.downloadContent(BUSINESS);
            case "SPORT":
                content.downloadContent(SPORT);
        }
    }

    public void downloadContent(String url) {
        try {
            new Connection().execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
