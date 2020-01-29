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
import com.example.labmedixapplication.activities.RegisterActivity;

public class CheakPhoneUserFragment extends Fragment {

    ImageView image_view_back;
    TextView text_view_edit;
    ImageView to_start;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cheak_phone_user, container, false);
        image_view_back = v.findViewById(R.id.image_view_back);
        text_view_edit = v.findViewById(R.id.text_view_edit);
        to_start = v.findViewById(R.id.to_start);

        to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main ,new UserInformationFragment()).commit();
            }
        });
        text_view_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        image_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }
}
