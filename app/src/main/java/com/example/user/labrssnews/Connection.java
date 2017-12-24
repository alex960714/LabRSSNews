package com.example.user.labrssnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Connection extends AsyncTask<String, Void, List<NewsRecord>> {

    @Override
    protected List<NewsRecord> doInBackground(String... params) {
        List<NewsRecord> items = new ArrayList<>();

        try {
            URL url = new URL(params[0]);
            String content = getContent(url);
            items = retrieveItems(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    protected void onPostExecute(List<NewsRecord> feedItems) {
        super.onPostExecute(feedItems);
        NewsAdapter adapter = new NewsAdapter(MainActivity.class, feedItems);
        lvMain.setAdapter(adapter);
    }

    private String getContent(URL url) {
        String content = "";
        BufferedReader reader = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            final StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            content = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    private Bitmap getImage(URL url) {
        Bitmap image = null;
        try {
            InputStream input = url.openStream();
            image = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    private List<NewsRecord> retrieveItems(String content) {
        List<NewsRecord> items = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(content));
            Document document = docBuilder.parse(is);
            document.getDocumentElement().normalize();

            NodeList elements = document.getElementsByTagName("item");
            for (int i = 0; i < elements.getLength() && i <= 10; i++) {
                Node item = elements.item(i);
                String title = getItemElement(item, "title");
                String description = getItemElement(item, "description");
                String link = getItemElement(item, "link");

                NewsRecord newsRecord = new NewsRecord(title, description, link);
                items.add(newsRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    private String getItemElement(Node item, String param) {
        return ((Element) item).getElementsByTagName(param).item(0).getTextContent();
    }
}
