package com.example.user.labrssnews;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<NewsRecord> data = new ArrayList<>();
    private Fragment owner;

    public NewsAdapter(List<NewsRecord> data, Fragment owner) {
        super();
        this.data = data;
        this.owner = owner;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItemRecord holder;

        if (convertView == null) {
            LayoutInflater inflater = owner.getActivity().getLayoutInflater();
            convertView = inflater.inflate(R.layout.news_show, null);
            holder = new NewsItemRecord();
            holder.setTitle((TextView) convertView.findViewById(R.id.newsTitle));
            holder.setDescription((TextView) convertView.findViewById(R.id.newsDescription));
            holder.setImage((ImageView) convertView.findViewById(R.id.newsImage));
            convertView.setTag(holder);
        } else {
            holder = (NewsItemRecord) convertView.getTag();
        }

        NewsRecord item = data.get(position);
        holder.getTitle().setText(item.getTitle());
        holder.getDescription().setText(item.getDescription());
        holder.getImage().setImageBitmap(item.getImage());
        holder.setLink(item.getLink());

        setOnClickListener(convertView);

        return convertView;
    }



    private void setOnClickListener(View newsItem) {
        newsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = ((NewsItemRecord) view.getTag()).getLink();
                ((NewsContentShow) owner).openWebView(link);
            }
        });
    }
}
