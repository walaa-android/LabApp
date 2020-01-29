package com.example.labmedixapplication.model;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.ReminderListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ModelSheet extends BottomSheetDialogFragment {

    LinearLayout layout_sheet;
    EditText edit_text_day;
    EditText edit_text_time;
    ImageView confirm_time;
    FirebaseAuth mAuth;
    DatabaseReference informationRef , informationRefDay;
    List<String> dayVisit;
    List<String> timeVisit;
    String day, time;
    List <String> displayTime ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.select_time, container, false);
        confirm_time = v.findViewById(R.id.confirm_time);
        edit_text_day = v.findViewById(R.id.edit_text_day);
        edit_text_time = v.findViewById(R.id.edit_text_time);
        layout_sheet = v.findViewById(R.id.layout_sheet);

        dayVisit = new ArrayList<>();
        timeVisit = new ArrayList<>();
        displayTime = new ArrayList<>();




        confirm_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!TextUtils.isEmpty(edit_text_day.getText()) && !TextUtils.isEmpty(edit_text_time.getText())) {


                    String day = edit_text_day.getText().toString();
                    String time = edit_text_time.getText().toString();

                    layout_sheet.setVisibility(View.GONE);
                    getActivity().findViewById(R.id.cont_sec).setVisibility(View.GONE);
                    getActivity().findViewById(R.id.confirm_image_view).setVisibility(View.VISIBLE);
                    TextView text_view_day = getActivity().findViewById(R.id.text_view_day);
                    text_view_day.setText(day);
                    TextView text_view_time = getActivity().findViewById(R.id.text_view_time);
                    text_view_time.setText(time);

                    getActivity().findViewById(R.id.materialCardView).setVisibility(View.VISIBLE);


                }

                confirm(v);
            }
        });
        return v;

    }


    public void confirm(View view) {

        if (TextUtils.isEmpty(edit_text_day.getText())){
            edit_text_day.setError(getString(R.string.required));

          if  (TextUtils.isEmpty(edit_text_time.getText())){
                edit_text_time.setError(getString(R.string.required));

            }
        }
        else {

          getActivity().findViewById(R.id.materialCardView).setVisibility(View.VISIBLE);
       //   for (int i =0 ; i< timeVisit.size() ; i++) {
              day = edit_text_day.getText().toString();
              time = edit_text_time.getText().toString();
              mAuth = FirebaseAuth.getInstance();
              informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
                    .child(mAuth.getUid()).child("Time Visit");
            informationRefDay = FirebaseDatabase.getInstance().getReference("userInformation")
                    .child(mAuth.getUid()).child("Day Visit");
            timeVisit.add(time);
            dayVisit.add(day);

              informationRefDay.setValue(dayVisit);
                      /*.addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()) {
                          Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
                      }
                  }
              });*/
              informationRef.setValue(timeVisit);
        }


        informationRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot dss: dataSnapshot.getChildren()){
                        String timeValue = dss.getValue(String.class);
                        displayTime.add(timeValue);
                    }
                }

                StringBuilder stringBuilder = new StringBuilder();

                for (int i =0 ; i<displayTime.size() ; i++){
                    stringBuilder.append(displayTime.get(i) + " +");
                }

                Toast.makeText(getContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


          /*  if (! nameLab . isEmpty()){

                 informationRef.setValue(nameLab);
                 informationRef.setValue(timeVisit);
             }*/





    }
}
