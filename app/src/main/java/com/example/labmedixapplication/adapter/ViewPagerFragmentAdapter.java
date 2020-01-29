package com.example.labmedixapplication.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.fragment.FirstOverViewFragment;
import com.example.labmedixapplication.fragment.SecoundOverViewFragment;
import com.example.labmedixapplication.fragment.ThirdOverViewFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ViewPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            //    ImageView btn_start = get.findViewById(R.id.btn_start);
            //    btn_start.setVisibility(View.VISIBLE);
                return new FirstOverViewFragment();
            case 1:
                return new SecoundOverViewFragment();
            case 2:
              return new ThirdOverViewFragment();

        }
        return new ThirdOverViewFragment();

    }

    @Override
    public int getCount() {
        return 3;

    }
}
