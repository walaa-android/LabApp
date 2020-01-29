package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.labmedixapplication.R;
import com.example.labmedixapplication.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInformationActivity extends AppCompatActivity {


    ImageView image_view_back, to_start;
    EditText edit_text_email, edit_text_username, edit_text_phone;
    DatabaseReference informationRef;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    boolean checked = true;
    String user, user_email;
    String name, email ,  birth , gender , photo ;
    SharedPreferences.Editor editor;
   // Uri photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);


        edit_text_username = findViewById(R.id.edit_text_username);
        edit_text_email = findViewById(R.id.edit_text_email);
        to_start = findViewById(R.id.to_start);
        image_view_back = findViewById(R.id.image_view_back);

        informationRef = FirebaseDatabase.getInstance().getReference("userInformation");
        sharedPreferences = getSharedPreferences("My Login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mAuth = FirebaseAuth.getInstance();


        to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(edit_text_username.getText())) {
                    edit_text_username.setError(getString(R.string.required));
                } else if (TextUtils.isEmpty(edit_text_email.getText())) {
                    edit_text_email.setError(getString(R.string.required));
                } else {

                    saveInfo();
                    Intent intent = new Intent(UserInformationActivity.this, HomeActivity.class);
                    startActivity(intent);
                }


            }


        });

        image_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserInformationActivity.this, AthunPhoneActivity.class);
                startActivity(intent1);
            }
        });

    }


    private void saveInfo() {
        name = edit_text_username.getText().toString();
        email = edit_text_email.getText().toString();
        birth= "Not Added";
        gender = "Not Added";
        photo="Not added";

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)) {

            UserModel userModel = new UserModel(name, email , birth , gender, photo);
            informationRef.child(FirebaseAuth.getInstance().getUid()).setValue(userModel);
            editor.putString("user",user);
            editor.putString("email" , email);
            editor.putString("birth" ,birth);
            editor.putString("gender" , gender);
            editor.putString("photo" , photo);
            editor.apply();

        }

    }

}



