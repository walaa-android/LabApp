package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReminderListActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference informationRef , informationRefDay;
    List <String> displayTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);

        displayTime = new ArrayList<>();
        informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
                .child(mAuth.getUid()).child("Time Visit");
        informationRefDay = FirebaseDatabase.getInstance().getReference("userInformation")
                .child(mAuth.getUid()).child("Day Visit");



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


                //  Toast.makeText(ReminderListActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void add(View view) {
    }

    public void back(View view) {
    }
}
