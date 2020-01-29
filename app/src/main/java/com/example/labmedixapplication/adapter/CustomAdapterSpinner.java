package com.example.labmedixapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.labmedixapplication.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapterSpinner extends ArrayAdapter <String > {
    private Context context;
    private String[] country_code;
    private int[] country_flag;
    private  View view;



    public CustomAdapterSpinner(Context context, int[] country_flag, String[]country_code) {
        super(context , R.layout.layout_code_picker , country_code);
        this.context = context;
        this.country_flag = country_flag;
        this.country_code = country_code;
    }




    @SuppressLint("InflateParams")
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

           assert inflater != null;
      //     view =inflater.inflate(R.layout.list_countey_layout , null);

      //      TextView countryCode = view.findViewById(R.id.text_view_code_country);
            //                ImageView flagCountry= view.findViewById(R.id.image_view_flag);

        //    countryCode.setText(country_code[position]);
        //   flagCountry.setImageResource(country_flag[position]);
        }
        return view;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
       //     view = inflater.inflate(R.layout.list_countey_layout, null);

       //     TextView countryCode = view.findViewById(R.id.text_view_code_country);
       //     ImageView flagCountry = view.findViewById(R.id.image_view_flag);

        //    countryCode.setText(country_code[position]);
        //    flagCountry.setImageResource(country_flag[position]);
        }
        return view;
    }

}
