package com.example.labmedixapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.fragment.ProfileFragment;

public class ReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
    }

    public void back(View view) {

       getSupportFragmentManager().beginTransaction().replace(R.id.remin , new ProfileFragment()).commit();




    }

    public void add(View view) {

        Intent intent = new Intent(ReminderActivity.this , AddReminderActivity.class);
        startActivity(intent);
    }
}
