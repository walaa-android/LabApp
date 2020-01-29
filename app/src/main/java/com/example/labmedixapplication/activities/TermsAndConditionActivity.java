package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.fragment.ProfileFragment;
import com.example.labmedixapplication.model.Lists;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class TermsAndConditionActivity extends AppCompatActivity {

    TextView text_view_terms;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);

         text_view_terms =findViewById(R.id.text_view_terms);

         firebaseFirestore = FirebaseFirestore.getInstance();
         documentReference = firebaseFirestore.collection("Terms Condition").document("terms");
         documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
             @Override
             public void onSuccess(DocumentSnapshot documentSnapshot) {
                 if(documentSnapshot.exists()){
                     String term = String.valueOf(documentSnapshot.getData());
                     text_view_terms.setText(term) ;
                     Toast.makeText(TermsAndConditionActivity.this, "Document exists", Toast.LENGTH_SHORT).show();


                 }else {
                     Toast.makeText(TermsAndConditionActivity.this, "Document dose not exists", Toast.LENGTH_SHORT).show();
                 }

             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(TermsAndConditionActivity.this, "failer to load data", Toast.LENGTH_SHORT).show();

                 }
         });
    }

    public void back(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id.term, new ProfileFragment()).commit();

    }
}
