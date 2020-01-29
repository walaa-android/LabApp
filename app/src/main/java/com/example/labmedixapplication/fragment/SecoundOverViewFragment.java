package com.example.labmedixapplication.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;

public class SecoundOverViewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v =inflater.inflate(R.layout.fragment_secound_over_view, container, false);
      // ImageView btn_start = getActivity().findViewById(R.id.btn_start);
       // TextView text_skip = getActivity().findViewById(R.id.text_skip);
      //  text_skip.setVisibility(View.VISIBLE);
      //  btn_start.setVisibility(View.GONE);

        return v;
    }

}
