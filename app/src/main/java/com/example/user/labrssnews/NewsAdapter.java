package com.example.user.labrssnews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private List<NewsRecord> items;
    private LayoutInflater inflater;
    private Context context;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView linkTextView;

    public NewsAdapter(Context context, List<NewsRecord> items) {
        this.items = items;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.news_show, parent, false);
        }

        NewsRecord newsRecord = items.get(position);
        titleTextView = (TextView)v.findViewById(R.id.newsTitleTextView);
        descriptionTextView = (TextView)v.findViewById(R.id.newsDescriptionTextView);
        linkTextView = (TextView)v.findViewById(R.id.linkTextView);
        titleTextView.setText(newsRecord.getTitle());
        descriptionTextView.setText(newsRecord.getDescription());
        int color = ContextCompat.getColor(context, R.color.colorPrimaryDark);
        descriptionTextView.setTextColor(color);
        linkTextView.setTextColor(color);
        linkTextView.setText(newsRecord.getLink());
        return v;
    }
}