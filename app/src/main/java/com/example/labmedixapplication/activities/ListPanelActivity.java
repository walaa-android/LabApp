package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.adapter.RecycleViewFirestoreAdapter;
import com.example.labmedixapplication.adapter.RecycleViewHomeAdapter;
import com.example.labmedixapplication.fragment.CheakPhoneUserFragment;
import com.example.labmedixapplication.fragment.HomeFragment;
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

import java.util.ArrayList;
import java.util.List;

public class ListPanelActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecycleViewHomeAdapter recycleViewHomeAdapter;
    ImageView imageView;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference ;
    RecycleViewFirestoreAdapter recycleViewFirestoreAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_panel);

        recyclerView = findViewById(R.id.recycler_all);
        imageView = findViewById(R.id.image_view_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext() , HomeActivity.class);
                startActivity(intent);


            }
        });

        /*data = new ArrayList<>();
        recycleViewHomeAdapter = new RecycleViewHomeAdapter(data, this);

        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.hurmonal, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.hometrology, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
        data.add(new Lists(R.drawable.cheack_up, "Cheackup Tests", "27 Avalible Test", R.drawable.ic_next));
*/


       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      // recyclerView.setLayoutManager(linearLayoutManager);
       // recyclerView.setAdapter(recycleViewHomeAdapter);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.item_animation_from_bottom);

        recyclerView.startAnimation(loadAnimation);
        setUpTestPanel();
        onStart();


    }
    private void setUpTestPanel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference =firebaseFirestore.collection("allTypePanel");
        Query query = collectionReference.orderBy("type" , Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Lists> options = new FirestoreRecyclerOptions.Builder<Lists>()
                .setQuery(query, Lists.class)
                .build();
        recycleViewFirestoreAdapter = new RecycleViewFirestoreAdapter(options);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleViewFirestoreAdapter);


        recycleViewFirestoreAdapter.setOnItemClickListener(new RecycleViewFirestoreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Lists lists = documentSnapshot.toObject(Lists.class);
                String id = documentSnapshot.getId();
                String path=documentSnapshot.getReference().getPath();
               Toast.makeText(ListPanelActivity.this, "id : " + id, Toast.LENGTH_SHORT).show();
               Intent intent = new Intent ( ListPanelActivity.this , CheckUpPanelActivity.class);
               startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {

        super.onStart();
        recycleViewFirestoreAdapter.startListening();
        Log.d("DDDD" , "Done");

    }

    @Override
    public void onStop() {
        super.onStop();
        recycleViewFirestoreAdapter.stopListening();
    }
}
