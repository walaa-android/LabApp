package com.example.labmedixapplication.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.HomeActivity;

public class LabrotoryInformationFragment extends Fragment {

    ImageView to_start , image_view_back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v=  inflater.inflate(R.layout.fragment_labrotory_information, container, false);
        image_view_back= v.findViewById(R.id.image_view_back);
        image_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , CheakPhoneNoFragment.class);
                startActivity(intent);

            }
        });

      to_start = v.findViewById(R.id.to_start);
      to_start.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getContext() , HomeActivity.class);
              startActivity(intent);
          }
      });

        return v;
    }

}
