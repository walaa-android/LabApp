package com.example.labmedixapplication.model;

import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ModelSheetEditEmail extends BottomSheetDialogFragment {

    LinearLayout layout_sheet;
    DatabaseReference informationRef;
    FirebaseAuth mAuth;
    String email;
    EditText edit_text_email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_edit_email, container, false);
        final ImageView save = v.findViewById(R.id.save);
        edit_text_email = v.findViewById(R.id.edit_text_email);
        layout_sheet = v.findViewById(R.id.layout_sheet);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(email);

                layout_sheet.setVisibility(View.GONE);


            }
        });
        return v;

    }


    private void updateData( String email) {

        informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
                .child(FirebaseAuth.getInstance().getUid());
        email = edit_text_email.getText().toString();
        informationRef.child("userEmail").setValue(email);









        Toast.makeText(getContext(), " Done" + email, Toast.LENGTH_SHORT).show();

    }

}
