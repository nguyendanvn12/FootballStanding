package com.nguyendan.footballstanding.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragmentList;
    public PagerAdapter(@NonNull FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.mfragmentList = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

}
