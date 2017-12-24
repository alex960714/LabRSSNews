package com.example.user.labrssnews;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button catBtn;
    private ListView newsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsLV = (ListView)findViewById(R.id.newsListView);
        catBtn = (Button)findViewById(R.id.categoryButton);

        catBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowCategoriesActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        String category = getIntent().getStringExtra("category");
        if (category == null){
            category = "All";
        }
        ((TextView)findViewById(R.id.titleTextView)).setText(category);
        new NewsSource(category);
    }

    public class NewsConnection extends AsyncTask<String, Void, List<NewsRecord>> {

        @Override
        protected List<NewsRecord> doInBackground(String... str) {
            List<NewsRecord> items = new ArrayList<>();
            SyndFeedInput input = new SyndFeedInput();
            try {
                URL url = new URL(str[0] + ".rss");
                SyndFeed feed = input.build(new XmlReader(url));
                List<SyndEntry> entries = feed.getEntries();
                for (SyndEntry entry : entries) {
                    String title = entry.getTitle();
                    Date publishedDate = entry.getPublishedDate();
                    String link = entry.getLink();
                    String description = entry.getDescription().getValue();
                    NewsRecord item = new NewsRecord(title, link, description.replaceAll("&quot;",
                            "'").replaceAll("More", "."), publishedDate);
                    items.add(item);
                }
            } catch (FeedException | IOException e) {
                e.printStackTrace();
            }
            System.out.println(items);
            return items;
        }

        @Override
        protected void onPostExecute(List<NewsRecord> feedItems) {

            super.onPostExecute(feedItems);
            NewsAdapter adapter = new NewsAdapter(MainActivity.this, feedItems);
            newsLV.setAdapter(adapter);
        }
    }

    public class NewsSource {
        private static final String BASE_PATH = "https://news.yandex.ru/";
        public static final String ALL = BASE_PATH + "index.html";
        public static final String TOP = BASE_PATH + "index.html";
        public static final String POLITICS = BASE_PATH + "politics.html";
        public static final String BUSINESS = BASE_PATH + "business.html";
        public static final String SPORT = BASE_PATH + "sport.html";

        public NewsSource(String pageNumber) {
            switch (pageNumber) {
                case "All":
                    downloadContent(ALL);
                    break;
                case "Top":
                    downloadContent(TOP);
                    break;
                case "Politics":
                    downloadContent(POLITICS);
                    break;
                case "Business":
                    downloadContent(BUSINESS);
                    break;
                case "Sport":
                    downloadContent(SPORT);
                    break;
            }
        }

        public void downloadContent(String url) {
            try {
                new NewsConnection().execute(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
