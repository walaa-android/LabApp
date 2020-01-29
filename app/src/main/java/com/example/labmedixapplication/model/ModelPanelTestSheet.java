package com.example.labmedixapplication.model;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.TermsAndConditionActivity;
import com.example.labmedixapplication.adapter.PanelViewPagerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ModelPanelTestSheet extends BottomSheetDialogFragment {

    LinearLayout layout_sheet;
    ViewPager viewPager;
    TabLayout tab_layout;
    TextView textView;
   @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.bottom_sheet_check_panel, container, false);
       viewPager = v.findViewById(R.id.view_pager);
       tab_layout=v.findViewById(R.id.tab_layout);
        viewPager.setAdapter(new PanelAdapter());

       tab_layout.setupWithViewPager(viewPager);

       tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
       return v;
   }
    //  viewPager.setAdapter(new PanelViewPagerAdapter(getFragmentManager());
    //PanelViewPagerAdapter panelViewPagerAdapter =new PanelViewPagerAdapter(getActivity().getSupportFragmentManager());
    //  viewPager.setAdapter(panelViewPagerAdapter);
    //   tab_layout.setupWithViewPager(viewPager);
    //  Log.d("DDDD" , "Done");



    class PanelAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull final ViewGroup container, int position) {
       //  textView = new TextView(getContext());

          //  viewPager.setText(" Walaaa ");

          //  viewPager.setAdapter(new PanelViewPagerAdapter());
        /*  String overView = null, test = null , detiles = null  ;

           final BottomSheetModel bottomSheetModel =new BottomSheetModel(overView , test , detiles);

            FirebaseFirestore firebaseFirestore;
            DocumentReference documentReference;

            firebaseFirestore = FirebaseFirestore.getInstance();
            documentReference = firebaseFirestore.collection("overviewpanel").document("nY9ziMVrurgvzijOstVH");
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.exists()){
                      //  String term = String.valueOf(documentSnapshot.getData());
                        //  textView.setText(term);
                        container.addView(textView);
                        Toast.makeText(getContext(), "Document exists", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getContext(), "Document dose not exists", Toast.LENGTH_SHORT).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "failer to load data", Toast.LENGTH_SHORT).show();

                }
            });*/


            return viewPager;
        }
    }

}





