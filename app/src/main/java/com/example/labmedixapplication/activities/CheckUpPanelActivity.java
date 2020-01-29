package com.example.labmedixapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.adapter.RecycleViewCheckPanelAdapter;
import com.example.labmedixapplication.adapter.RecycleViewFirestoreAdapter;
import com.example.labmedixapplication.adapter.RecycleViewHomeAdapter;
import com.example.labmedixapplication.model.Lists;
import com.example.labmedixapplication.model.ModelPanelTestSheet;
import com.example.labmedixapplication.model.ModelSheet;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CheckUpPanelActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference ;
    RecycleViewCheckPanelAdapter recycleViewCheckPanelAdapter;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up_panel);


        recyclerView = findViewById(R.id.recycler_all);
        imageView = findViewById(R.id.image_view_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext() , ListPanelActivity.class);
                startActivity(intent);


            }
        });

        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.item_animation_from_bottom);

        recyclerView.startAnimation(loadAnimation);
        setUpTestPanel();
        onStart();


    }




    private void setUpTestPanel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("checkUpPanel");
        Query query = collectionReference.orderBy("type", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Lists> options = new FirestoreRecyclerOptions.Builder<Lists>()
                .setQuery(query, Lists.class)
                .build();
        recycleViewCheckPanelAdapter = new RecycleViewCheckPanelAdapter(options);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleViewCheckPanelAdapter);


        recycleViewCheckPanelAdapter.setOnItemClickListener(new RecycleViewCheckPanelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                ModelPanelTestSheet modelPanelTestSheet = new ModelPanelTestSheet();
                modelPanelTestSheet.show(getSupportFragmentManager(),modelPanelTestSheet.getTag());



            }
        });
    }

    @Override
    public void onStart() {

        super.onStart();
        recycleViewCheckPanelAdapter.startListening();
        Log.d("DDDD" , "Done");

    }

    @Override
    public void onStop() {
        super.onStop();
        recycleViewCheckPanelAdapter.stopListening();
    }

}
