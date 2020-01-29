package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.adapter.ViewPagerAdapter;
import com.example.labmedixapplication.adapter.ViewPagerFragmentAdapter;
import com.example.labmedixapplication.fragment.ChatFragment;
import com.example.labmedixapplication.fragment.HomeFragment;
import com.example.labmedixapplication.fragment.ProfileFragment;
import com.example.labmedixapplication.fragment.ResultFragment;
import com.example.labmedixapplication.fragment.VisitFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView  bottom_navigation;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottom_navigation= findViewById(R.id.bottom_navigation);
      FrameLayout viewPager = findViewById(R.id.view_pager);
    //  viewPagerAdapter = new ViewPagerAdapter(this , getSupportFragmentManager());
  //  viewPager.setAdapter(viewPagerAdapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new HomeFragment()).commit();

      bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                  switch (item.getItemId()) {
                      case R.id.action_home:

                          getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new HomeFragment()).commit();
                    //   bottom_navigation.setSelectedItemId(R.id.action_home);
                      //    setTitle("Home");


                          break;

                      case R.id.action_reports:


                          getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new ResultFragment()).commit();
                //    bottom_navigation.setSelectedItemId(R.id.action_reports);

                      //    setTitle("Reports");

                          break;

                      case R.id.action_chats:

                          getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new ChatFragment()).commit();
                  //      bottom_navigation.setSelectedItemId(R.id.action_chats);
                          bottom_navigation.getItemTextAppearanceActive();


                     //     setTitle("Chats");

                          break;
                      case R.id.action_visit:
                          Intent intent  = new Intent(getApplicationContext(), MapsActivity.class);
                          startActivity(intent);
                          break;
                      case R.id.action_account:

                          getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new ProfileFragment()).commit();
                    //   bottom_navigation.setSelectedItemId(R.id.action_account);
                          bottom_navigation.getItemTextAppearanceActive();

                          setTitle("Profile");

                          break;
                  }
                  return false;
              }
      });
    }

    private void setTintItemSelect(MenuItem item){

        //


        //

    }

    public void visit(View view) {
     //   getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, new VisitFragment()).commit();
        Intent intent  = new Intent(this , MapsActivity.class);
        startActivity(intent);


    }
}