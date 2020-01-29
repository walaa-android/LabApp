package com.example.labmedixapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.adapter.RecycleViewFirestoreAdapter;
import com.example.labmedixapplication.adapter.RecycleViewFirestoreTestAdapter;
import com.example.labmedixapplication.adapter.RecycleViewHomeAdapter;
import com.example.labmedixapplication.model.ListCondition;
import com.example.labmedixapplication.model.Lists;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

public class TestConditionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference ;
    RecycleViewFirestoreAdapter recycleViewFirestoreAdapter;
    List<Lists> data;
    RecycleViewFirestoreTestAdapter recycleViewFirestoreTestAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_condition);
        recyclerView=findViewById(R.id.recycler_view);

      /*  data = new ArrayList<>();
        recycleViewHomeAdapter = new RecycleViewHomeAdapter(data, this);*/

     /*   data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));
        data.add(new Lists( "Lipid profile", R.drawable.ic_next));


        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.item_animation_from_bottom);

        recyclerView.startAnimation(loadAnimation);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recycleViewHomeAdapter);*/

        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.item_animation_from_bottom);

        recyclerView.startAnimation(loadAnimation);
        setUpTestPanel();
      onStart();

    }

    private void setUpTestPanel() {

            firebaseFirestore = FirebaseFirestore.getInstance();
            collectionReference =firebaseFirestore.collection("Test Condition");
            Query query = collectionReference.orderBy("type" , Query.Direction.DESCENDING);
            FirestoreRecyclerOptions<ListCondition> options = new FirestoreRecyclerOptions.Builder<ListCondition>()
                    .setQuery(query, ListCondition.class)
                    .build();
        recycleViewFirestoreTestAdapter = new RecycleViewFirestoreTestAdapter(options);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(recycleViewFirestoreTestAdapter);


        recycleViewFirestoreTestAdapter.setOnItemClickListener(new RecycleViewFirestoreTestAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(DocumentSnapshot documentSnapshot, int position) {


                }
            });


    }




    public void back(View view) {
        Intent intent = new Intent( getApplicationContext() , HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void onStart() {

        super.onStart();
        recycleViewFirestoreTestAdapter.startListening();
        Log.d("DDDD" , "Done");

    }

    @Override
    public void onStop() {
        super.onStop();
        recycleViewFirestoreTestAdapter.stopListening();
    }
}
