package com.example.labmedixapplication.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.HomeActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class UserInformationFragment extends Fragment {

   // static private final String TAG = UserInformationFragment.class.getSimpleName();

    private final  static int  PICK_IMG_REQUEST =100;

    FirebaseStorage firebaseStorage;
    private ImageView image_view_back , to_start;
EditText edit_text_email , edit_text_username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view =inflater.inflate(R.layout.fragment_user_information, container, false);
      edit_text_username = view.findViewById(R.id.edit_text_username);
      edit_text_email=view.findViewById(R.id.edit_text_email);

       firebaseStorage  = FirebaseStorage.getInstance();
        StorageReference mainRef = firebaseStorage.getReference();



        to_start= view.findViewById(R.id.to_start);
        to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   if (TextUtils.isEmpty(edit_text_username.getText())) {
                    edit_text_username.setError(getString(R.string.required));
                } else if (TextUtils.isEmpty(edit_text_email.getText())) {
                    edit_text_email.setError(getString(R.string.required));

                } else {
                    Intent dataPickerIntent = new Intent(Intent.ACTION_PICK);
                    dataPickerIntent.setType("userInformation/*");
                  }   startActivityForResult(dataPickerIntent, PICK_IMG_REQUEST);*/

                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);



            }
        });
      image_view_back= view.findViewById(R.id.image_view_back);
      image_view_back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag ,new CheakPhoneUserFragment()).commit();

          }
      });


      return view;
    }



    }


