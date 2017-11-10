package com.example.vishwanath.CustomAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.example.vishwanath.Fragments.FragmentFirst;
import com.example.vishwanath.Fragments.FragmentForth;
import com.example.vishwanath.Fragments.FragmentSecond;
import com.example.vishwanath.Fragments.FragmentThird;

import java.util.List;

/**
 * Created by android-linux-mv on 8/11/17.
 */

public class ViewPagerFSFAdapter extends FragmentPagerAdapter {
    private List<Fragment> fList;
    private FragmentManager fragmentManager;
    private int numberOfPages;
    public ViewPagerFSFAdapter(FragmentManager fragmentManager,
                               List<Fragment> fList) {
        super(fragmentManager);
        this.fList = fList;
        this.fragmentManager = fragmentManager;
        numberOfPages=4;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            switch (position) {
                case 1:
                    fList.set(position, new FragmentSecond());
                    return fList.get(position);
                case 2:
                    fList.set(position, new FragmentThird());
                    return fList.get(position);
                case 3:
                    fList.set(position, new FragmentForth());
                    return fList.get(position);
                default:
                    fList.set(position, new FragmentFirst());
                    return fList.get(position);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        assert (0 <= position && position < fList.size());
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(fList.get(position));
        trans.commit();
        fList.add(position, null);
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = getItem(position);
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(container.getId(), fragment, "");
        trans.commit();
        return fragment;
    }

    @Override
    public boolean isViewFromObject(View view, Object fragment) {
        return ((Fragment) fragment).getView() == view;
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }
}