package com.example.labmedixapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.AthunPhoneActivity;

public class ThirdOverViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView btn_start = getActivity().findViewById(R.id.btn_start);
        btn_start.setVisibility(View.VISIBLE);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , AthunPhoneActivity.class);
                startActivity(intent);
            }
        });

        View v = inflater.inflate(R.layout.fragment_third_over_view, container, false);
        TextView text_skip = getActivity().findViewById(R.id.text_skip);
        text_skip.setVisibility(View.GONE);

   return v;


    }


}
