package com.example.labmedixapplication.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.model.UserModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class MyDatePickerFragment extends DialogFragment {

    DatabaseReference informationRef;
    String date;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }
    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {

                  date="" + view.getDayOfMonth()+" / " + (view.getMonth()+1)+" / " + view.getYear();

                   TextView text_view_birth=getActivity().findViewById(R.id.text_view_birth);

                    text_view_birth.setText(date);
                    updateData(date);



                }
            };

    private void updateData( String birthDate) {

        informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
                .child(FirebaseAuth.getInstance().getUid());
        informationRef.child("birth").setValue(date);


      //  informationRef.child("")

    /*    informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
                .child(FirebaseAuth.getInstance().getUid());
        birthDate = date.toString();
        informationRef.child("birth").setValue(birthDate);
       UserModel userModel=new UserModel();
        userModel.setBirth(birthDate);*/



    /*    Task<Void> birth =  FirebaseDatabase.getInstance().getReference("userInformation")
                .child(FirebaseAuth.getInstance().getUid()).getRef().setValue(birthDate);
        informationRef.child("birth").setValue(birth);*/






        Toast.makeText(getContext(), " Done" + date, Toast.LENGTH_SHORT).show();

    }


}