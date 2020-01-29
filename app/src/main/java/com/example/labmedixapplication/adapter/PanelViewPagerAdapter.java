package com.example.labmedixapplication.adapter;

import android.view.View;

import com.example.labmedixapplication.fragment.FirstOverViewFragment;
import com.example.labmedixapplication.fragment.SecoundOverViewFragment;
import com.example.labmedixapplication.fragment.ThirdOverViewFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class PanelViewPagerAdapter extends PagerAdapter {


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
   /* public PanelViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstOverViewFragment();
            case 1:
                return new SecoundOverViewFragment();
            case 2:
                return new ThirdOverViewFragment();
        }
        return new ThirdOverViewFragment();

    }*/

    @Override
    public int getCount() {
        return 3;
    }


}
