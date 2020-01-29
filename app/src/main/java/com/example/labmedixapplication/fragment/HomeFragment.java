package com.example.labmedixapplication.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.CheckUpPanelActivity;
import com.example.labmedixapplication.activities.ListPanelActivity;
import com.example.labmedixapplication.activities.TestConditionActivity;
import com.example.labmedixapplication.adapter.RecycleViewFirestoreAdapter;
import com.example.labmedixapplication.adapter.RecycleViewHomeAdapter;
import com.example.labmedixapplication.model.Lists;
import com.example.labmedixapplication.model.UserModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Collections;
import java.util.List;


public class HomeFragment extends Fragment {
    DatabaseReference informationRef;
    UserModel userModel;
    TextView textViewName;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference ;
    RecycleViewFirestoreAdapter recycleViewFirestoreAdapter;

    RecyclerView recyclerView;
    List<Lists> data;
    RecycleViewHomeAdapter recycleViewHomeAdapter;
    TextView textView;
    ImageView packag;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recycler_list);
        textViewName = v.findViewById(R.id.text_view_username);
        packag=v.findViewById(R.id.packag);

        packag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TestConditionActivity.class);
                startActivity(intent);
            }
        });


        textView = v.findViewById(R.id.see_all);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListPanelActivity.class);
                startActivity(intent);
            }
        });
      //  data = new ArrayList<>();
      //  recycleViewHomeAdapter = new RecycleViewHomeAdapter(data, getActivity());

     //  data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
      //  data.add(new Lists(R.drawable.hurmonal, "Hormonal Tests", "27 Avalible Test", R.drawable.ic_next));
      //  data.add(new Lists(R.drawable.hometrology, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));


     //   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
       // recyclerView.setLayoutManager(linearLayoutManager);
      //  recyclerView.setAdapter(recycleViewHomeAdapter);

     //   onStart();
        setUpTestPanel();
        return v;
    }

    private void setUpTestPanel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference =firebaseFirestore.collection("typePanel");
        Query query = collectionReference.orderBy("type" , Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Lists> options = new FirestoreRecyclerOptions.Builder<Lists>()
                .setQuery(query, Lists.class)
                .build();
        recycleViewFirestoreAdapter = new RecycleViewFirestoreAdapter(options);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
      //  recyclerView.setHasFixedSize(true);
     //   Log.d("DDDD" , "Done");
        recyclerView.setAdapter(recycleViewFirestoreAdapter);




        recycleViewFirestoreAdapter.setOnItemClickListener(new RecycleViewFirestoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Lists lists = documentSnapshot.toObject(Lists.class);
                String id = documentSnapshot.getId();
                String path=documentSnapshot.getReference().getPath();

               // Toast.makeText(getActivity(), "id : " + id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent ( getActivity() , CheckUpPanelActivity.class);
                startActivity(intent);
            }
        });



    }

  /*  @Override
    public void onStart() {
        informationRef = FirebaseDatabase.getInstance().getReference("userInformation");
        informationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
              //   userModel = snapshot.getValue(UserModel.class);
                  UserModel fullName = dataSnapshot.child(FirebaseAuth.getInstance().getUid()).getValue(UserModel.class);
                    textViewName.setText(fullName.getFullName());
                Log.d("DDDD" , fullName.getFullName());

               // }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        super.onStart();

    }*/


    @Override
    public void onStart() {

        super.onStart();


        informationRef = FirebaseDatabase.getInstance().getReference("userInformation");
        informationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                //   userModel = snapshot.getValue(UserModel.class);
                UserModel fullName = dataSnapshot.child(FirebaseAuth.getInstance().getUid()).getValue(UserModel.class);
//

                if (fullName != null) {
                    textViewName.setText(fullName.getFullName());
                }
                //  Log.d("DDDD" , fullName.getFullName());

                // }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recycleViewFirestoreAdapter.startListening();
        Log.d("DDDD" , "Done");

    }

    @Override
    public void onStop() {
        super.onStop();
        recycleViewFirestoreAdapter.stopListening();
    }
}


