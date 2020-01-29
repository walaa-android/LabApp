package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PolicyActivity extends AppCompatActivity {


    TextView text_view_policy;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        text_view_policy = findViewById(R.id.text_view_policy);

        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("Privacy Policy").document("Policy");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String policy = String.valueOf(documentSnapshot.getData());
                    text_view_policy.setText(policy) ;
                    Toast.makeText(PolicyActivity.this, "Document exists", Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(PolicyActivity.this, "Document dose not exists", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PolicyActivity.this, "failer to load data", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void back(View view) {
    }
}
