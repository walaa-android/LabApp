package com.example.labmedixapplication.adapter;

import android.content.Context;
import com.example.labmedixapplication.fragment.ChatFragment;
import com.example.labmedixapplication.fragment.HomeFragment;
import com.example.labmedixapplication.fragment.ProfileFragment;
import com.example.labmedixapplication.fragment.ResultFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomTabsAdapter extends FragmentPagerAdapter {

    private Context Context;

    public CustomTabsAdapter(Context context, FragmentManager fm) {
        super(fm);
       Context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               return new HomeFragment();
                case 1:
                return new ResultFragment();
            case 2:
                return new ChatFragment();
            case 3:
                return new ProfileFragment();
        }
        return new ProfileFragment();
    }

    @Override
    public int getCount() {
        return
                4;
    }
}
