package com.example.labmedixapplication.fragment;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.labmedixapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirstTabFragment extends Fragment {
    FirebaseStorage firebaseStorage;
    StorageReference storageRef;
    StorageReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView text_view_over_view;
        View v = inflater.inflate(R.layout.fragment_first_tab, container, false);
        text_view_over_view = v.findViewById(R.id.text_view_over_view);

     /*  storageRef  = firebaseStorage.getInstance().getReference("overview");
         ref = storageRef.child("overview.txt");
         ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {
                  downLoadFile();
                 }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {


             }
         });

    }


    public  void downLoadFile(){

    }*/
        return v;
    }
}