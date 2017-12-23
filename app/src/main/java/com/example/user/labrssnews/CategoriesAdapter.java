package com.example.user.labrssnews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoriesAdapter extends FragmentPagerAdapter {

    public CategoriesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsContentShow.newInstance(position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Top";
            case 2:
                return "Politics";
            case 3:
                return "Business";
            case 4:
                return "Sport";
        }
        return null;
    }
}