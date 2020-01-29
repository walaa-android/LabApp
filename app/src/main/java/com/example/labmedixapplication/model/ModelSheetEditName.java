package com.example.labmedixapplication.model;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class ModelSheetEditName extends BottomSheetDialogFragment {

    LinearLayout layout_sheet;
    DatabaseReference informationRef;
    FirebaseAuth mAuth;
    String name;
    EditText edit_text_username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_data, container, false);
        final ImageView save = v.findViewById(R.id.save);
        edit_text_username = v.findViewById(R.id.edit_text_username);
        layout_sheet = v.findViewById(R.id.layout_sheet);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(name);

                layout_sheet.setVisibility(View.GONE);


            }
        });
        return v;

    }


    private void updateData( String fullUserName) {

        informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
             .child(FirebaseAuth.getInstance().getUid());
        fullUserName = edit_text_username.getText().toString();
        informationRef.child("fullName").setValue(fullUserName);









                Toast.makeText(getContext(), " Done" + name, Toast.LENGTH_SHORT).show();

            }
}