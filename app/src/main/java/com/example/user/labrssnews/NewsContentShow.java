package com.example.user.labrssnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class NewsContentShow extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public NewsContentShow() {
    }

    public static NewsContentShow newInstance(int sectionNumber) {
        NewsContentShow fragment = new NewsContentShow();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        List<NewsRecord> data = NewsSource.getContent((Integer) this.getArguments().get(ARG_SECTION_NUMBER));


        ListView newsWall = (ListView) rootView.findViewById(R.id.newsWall);
        NewsAdapter adapter = new NewsAdapter(data.subList(1, data.size() - 1), this);
        fixScrollListView(newsWall);
        setListViewHeightBasedOnChildren(newsWall);

        newsWall.setAdapter(adapter);

        return rootView;
    }

    public void openWebView(String link) {
        Intent intent = new Intent(getActivity(), ShowNewsActivity.class);
        intent.putExtra("link", link);
        startActivityForResult(intent, 0);
    }

    private void fixScrollListView(ListView lv) {
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
