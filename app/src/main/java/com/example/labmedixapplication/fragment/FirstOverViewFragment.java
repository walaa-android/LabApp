package com.example.labmedixapplication.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;

public class FirstOverViewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_first_over_view, container, false);
      //  TextView text_skip = getActivity().findViewById(R.id.text_skip);
     //   text_skip.setVisibility(View.VISIBLE);
      //  ImageView btn_start = getActivity().findViewById(R.id.btn_start);
      //  btn_start.setVisibility(View.INVISIBLE);

        return v;
    }

}
