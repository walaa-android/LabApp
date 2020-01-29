package com.example.labmedixapplication.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ModelSheetEditGender extends BottomSheetDialogFragment {

    LinearLayout layout_sheet;
    DatabaseReference informationRef;
    FirebaseAuth mAuth;
    String gender;
    RadioButton radio_button_female , radio_button_male;
    TextView text_view_gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_gender, container, false);
        final ImageView save = v.findViewById(R.id.save);
        radio_button_female = v.findViewById(R.id.radio_button_female);
        radio_button_male= v.findViewById(R.id.radio_button_male);
        text_view_gender = getActivity().findViewById(R.id.text_view_gender);


        layout_sheet = v.findViewById(R.id.layout_sheet);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radio_button_female .isChecked()){

                    gender = "Female";

                    text_view_gender.setText(gender);

                }else{
                    gender ="Male";
                    text_view_gender.setText(gender);
                }

                updateData(gender);

                layout_sheet.setVisibility(View.GONE);


            }
        });
        return v;

    }


    private void updateData( String gender) {

        informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
                .child(FirebaseAuth.getInstance().getUid());

        informationRef.child("gender").setValue(gender);









        Toast.makeText(getContext(), " Done" + gender, Toast.LENGTH_SHORT).show();

    }

}
