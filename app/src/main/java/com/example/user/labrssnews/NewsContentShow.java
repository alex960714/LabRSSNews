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

    public void openWebView(String link) {
        Intent intent = new Intent(getActivity(), ShowNewsActivity.class);
        intent.putExtra("link", link);
        startActivityForResult(intent, 0);
    }
}
