package com.example.pbl_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class pagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> items = new ArrayList<Fragment>();

    public pagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new FragmentFirst();
            case 1:
                return new FragmentSecond();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    public void addItem(Fragment item){
        items.add(item);
    }
}
