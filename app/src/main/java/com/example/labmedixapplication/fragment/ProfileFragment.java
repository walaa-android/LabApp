package com.example.labmedixapplication.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.EditProfileActivity;
import com.example.labmedixapplication.adapter.RecycleViewHomeAdapter;
import com.example.labmedixapplication.adapter.RecycleViewProfileAdapter;
import com.example.labmedixapplication.adapter.RecycleViewTowProfileAdapter;
import com.example.labmedixapplication.model.Lists;
import com.example.labmedixapplication.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {


    RecyclerView recyclerViewOne , recyclerViewtwo;
    List<Lists> contentProfile;
    RecycleViewProfileAdapter recycleViewProfileAdapter;
    RecycleViewTowProfileAdapter recycleViewTowProfileAdapter;
    List<Lists> contentTwoProfile;
    TextView textViewName;
    DatabaseReference informationRef;
    CircleImageView userImageView ;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewName = v.findViewById(R.id.textViewName);
        recyclerViewOne = v.findViewById(R.id.recycle_one);
        recyclerViewtwo = v.findViewById(R.id.recycle_two);
        userImageView = v.findViewById(R.id.image_view_user);

        userImageView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),EditProfileActivity.class);
                startActivity(intent);


            }
        });

        contentProfile = new ArrayList<>();
        recycleViewProfileAdapter = new RecycleViewProfileAdapter(contentProfile, getActivity());

      contentProfile.add(new Lists(R.drawable.visit, "My home visit", R.drawable.ic_next));
        contentProfile.add(new Lists(R.drawable.reminder, "Reminder", R.drawable.ic_next));
        contentProfile.add(new Lists(R.drawable.lang, "Language", R.drawable.ic_next));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewOne.setLayoutManager(linearLayoutManager);

        recycleViewProfileAdapter.setOnItemClickListener(new RecycleViewProfileAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {

                System.out.print(position);
            }

        });

        recyclerViewOne.setAdapter(recycleViewProfileAdapter);



        contentTwoProfile = new ArrayList<>();

        recycleViewTowProfileAdapter = new RecycleViewTowProfileAdapter(contentTwoProfile, getActivity());
       contentTwoProfile.add(new Lists(R.drawable.condition, "Terms & Conditions", R.drawable.ic_next));
        contentTwoProfile.add(new Lists(R.drawable.policy, "Privacy Policy", R.drawable.ic_next));
       // contentTwoProfile.add(new Lists(R.drawable.contact_us, "Help Center", R.drawable.ic_next));
        contentTwoProfile.add(new Lists(R.drawable.sign_out, "Sign Out", R.drawable.ic_next));
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerViewtwo.setLayoutManager(linearLayoutManager2);
        recyclerViewtwo.setAdapter(recycleViewTowProfileAdapter);

        recycleViewTowProfileAdapter.setOnItemClickListener(new RecycleViewTowProfileAdapter.OnItemClickListener() {

            @Override
            public void onItemClicked(View view, int position) {

            }
        } );



        onStart();

        return v;
    }

    @Override
    public void onStart() {


        informationRef = FirebaseDatabase.getInstance().getReference("userInformation");
        informationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             UserModel fullName = dataSnapshot.child(FirebaseAuth.getInstance().getUid()).getValue(UserModel.class);
                assert fullName != null;
                textViewName.setText(fullName.getFullName());
                sharedPreferences= ProfileFragment.this.getContext()
                        .getSharedPreferences("myShared" , MODE_PRIVATE);
                editor=sharedPreferences.edit();

            /*    Glide.with(ProfileFragment.this).load(
                        sharedPreferences.getString(
                                "url",
                                "https://console.firebase.google.com/u/1/project/lab-medix-final-project/storage/lab-medix-final-project.appspot.com/files~2FImage"))
                        .into(userImageView);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        super.onStart();
    }
}
