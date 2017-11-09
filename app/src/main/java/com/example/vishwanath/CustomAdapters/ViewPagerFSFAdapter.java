package com.example.vishwanath.CustomAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vishwanath.Fragments.FragmentFirst;
import com.example.vishwanath.Fragments.FragmentForth;
import com.example.vishwanath.Fragments.FragmentSecond;
import com.example.vishwanath.Fragments.FragmentThird;

/**
 * Created by android-linux-mv on 8/11/17.
 */
public class ViewPagerFSFAdapter extends FragmentPagerAdapter {
    int NUM_PAGES = 4;

    public ViewPagerFSFAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FragmentSecond();
            case 2:
                return new FragmentThird();
            case 3:
                return new FragmentForth();
            default:
                return new FragmentFirst();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}