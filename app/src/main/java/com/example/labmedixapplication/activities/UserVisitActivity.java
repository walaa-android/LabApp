package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.adapter.RecycleViewHomeVisitAdapter;
import com.example.labmedixapplication.model.Lists;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserVisitActivity extends AppCompatActivity {

    RecyclerView recyclerViewActive ;
    RecycleViewHomeVisitAdapter recycleViewHomeVisitAdapter;
    ArrayList <Lists> data;
    DatabaseReference informationRef , informationRefDay;
    FirebaseAuth mAuth;
    List <String> displayTime ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_visit);
        recyclerViewActive = findViewById(R.id.recyclerView);
        displayTime = new ArrayList<>();
       data = new ArrayList<>();
        recycleViewHomeVisitAdapter = new RecycleViewHomeVisitAdapter(data, this);




        mAuth = FirebaseAuth.getInstance();
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

                Toast.makeText(UserVisitActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        data.add(new Lists( "Msalam Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "Msalam Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "CIben Alhitam Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "Alarapy Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "Msalam Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "CIben Alhitam Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "Alarapy Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "Msalam Labrotory", "11 : 5 : 3"));
        data.add(new Lists( "CIben Alhitam Labrotory", "11 : 5 : 3"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewActive.setLayoutManager(linearLayoutManager);
        recyclerViewActive.setAdapter(recycleViewHomeVisitAdapter);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.item_animation_from_bottom);
        recyclerViewActive.startAnimation(loadAnimation);
        onStart();



    }
}
