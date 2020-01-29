package com.example.labmedixapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.fragment.CheakPhoneNoFragment;
import com.example.labmedixapplication.fragment.HomeFragment;
import com.example.labmedixapplication.fragment.ProfileFragment;

public class LabInformationActivity extends AppCompatActivity {

    static private final String TAG = LabInformationActivity.class.getSimpleName();

    ImageView to_start, image_view_back;
    EditText edit_text_name, edit_text_num, edit_text_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_information);
        edit_text_name = findViewById(R.id.edit_text_name);
        edit_text_num = findViewById(R.id.edit_text_num);
        edit_text_email = findViewById(R.id.edit_text_email);

        image_view_back = findViewById(R.id.image_view_back);
        image_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  getSupportFragmentManager().beginTransaction().replace(R.id.cheak, new CheakPhoneNoFragment()).commit();
Intent intent = new Intent (LabInformationActivity.this, AthunPhoneActivity.class);
startActivity(intent);

            }
        });

        to_start = findViewById(R.id.to_start);
        to_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edit_text_name.getText())) {
                    edit_text_name.setError(getString(R.string.required));
                } else if (TextUtils.isEmpty(edit_text_email.getText())) {
                    edit_text_email.setError(getString(R.string.required));
                } else if (TextUtils.isEmpty(edit_text_num.getText())) {
                    edit_text_num.setError(getString(R.string.required));

                } else {



                    Intent intent = new Intent(LabInformationActivity.this, HomeLabrotoryActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}
