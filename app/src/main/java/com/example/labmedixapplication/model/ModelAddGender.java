package com.example.labmedixapplication.model;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ModelAddGender extends BottomSheetDialogFragment {
    LinearLayout layout_sheet;
    DatePicker date_packer_user;
    TextView eText;
    ImageView save;
    String date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_add_gender, container, false);

        date_packer_user = v.findViewById(R.id.datePicker);
        save = v.findViewById(R.id.save);


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date_packer_user.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
             //       Toast.makeText(getContext(), "Selected Date:" + "//" + date_packer_user.getDayOfMonth() + "//" + (date_packer_user.getMonth() + 1) + "//" + date_packer_user.getYear(), Toast.LENGTH_SHORT).show();
                    date =""  + date_packer_user.getDayOfMonth() +" / " + date_packer_user.getDayOfMonth() +" / " + (date_packer_user.getMonth() + 1) + "/" + date_packer_user.getYear();

                    TextView text_view_birth = getActivity().findViewById(R.id.text_view_birth);
                    text_view_birth.setText(date);

                }
            });
            //    }else {
            //     Toast.makeText(getContext(), "Some thing wrong", Toast.LENGTH_SHORT).show();
            //     }
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(getContext(), "GGGG", Toast.LENGTH_SHORT).show();

                  //  Toast.makeText(getContext(), "Selected Date:" + "//" + date_packer_user.getDayOfMonth() + "//" + (date_packer_user.getMonth() + 1) + "//" + date_packer_user.getYear(), Toast.LENGTH_SHORT).show();

            /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    date_packer_user.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Toast.makeText(getContext(), "Selected Date:" +"//" + date_packer_user.getDayOfMonth()+"//"+ (date_packer_user.getMonth() + 1)+"//"+date_packer_user.getYear(), Toast.LENGTH_SHORT).show();

                        }
                    });*/
                    //    }else {
                    //     Toast.makeText(getContext(), "Some thing wrong", Toast.LENGTH_SHORT).show();
                    //     }
                }
            });






       /*  final ImageView save = v.findViewById(R.id.save);
        layout_sheet = v.findViewById(R.id.layout_sheet);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog

                layout_sheet.setVisibility(View.GONE);


            }
        });*/


        } return v;
    }
}
