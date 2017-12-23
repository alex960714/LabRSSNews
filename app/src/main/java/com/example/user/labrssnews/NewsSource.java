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

    public static List<NewsRecord> getContent(int pageNumber) {
        NewsSource content = new NewsSource();
        switch (pageNumber) {
            case 0:
                return content.downloadContent(ALL);
            case 1:
                return content.downloadContent(TOP);
            case 2:
                return content.downloadContent(POLITICS);
            case 3:
                return content.downloadContent(BUSINESS);
            case 4:
                return content.downloadContent(SPORT);
        }
        return null;
    }

    public List<NewsRecord> downloadContent(String url) {
        try {
            return new Connection().execute(url).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
