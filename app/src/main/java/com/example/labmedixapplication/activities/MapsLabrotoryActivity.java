package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.model.ModelSheet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MapsLabrotoryActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener , GoogleMap.OnMapClickListener{

    private GoogleMap mMap;
    private Marker marker_lab , marker_user;
    CardView materialCardView , materialCardViewSummery;
    ImageView confirm_image_view;
    ImageView confirm_time;
    TextView text_view_user_location , text_view_lab_name , text_view_lab_location;
    static private final String TAG = MapsLabrotoryActivity.class.getSimpleName();
    static private final int GET_POSITION_REQUEST_CODE = 1;
    FirebaseFirestore fireStore;
    DocumentReference documentReference;
    DocumentReference documentReferenceLab;
    private static final int REQUEST_PICK_USER_LOCATION = 101;
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String LOCATION = "location";
    private static  final String USER_LOCATION = "userlocation";
    private  static  final String  LAB_LOCATION ="lablocation";
    EditText edit_text_lab_location_text;
    private static final String Day = "day";
    ImageView cont_sec , con_add;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_labrotory);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        materialCardView= findViewById(R.id.materialCardView);
        confirm_image_view=findViewById(R.id.confirm_image_view);
        materialCardViewSummery=findViewById(R.id.materialCardViewSummery);
        confirm_time=findViewById(R.id.confirm_time);
        text_view_user_location = findViewById(R.id.text_view_user_location);
        edit_text_lab_location_text=findViewById(R.id.edit_text_lab_location_text);
        text_view_lab_name = findViewById(R.id.text_view_lab_name);
        cont_sec= findViewById(R.id.cont_sec);
        con_add=findViewById(R.id.con_add);
        text_view_lab_location= findViewById(R.id.text_view_lab_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment != null){

            mapFragment.getMapAsync(this);



            fireStore = FirebaseFirestore.getInstance();
            documentReference = fireStore.collection("Location").document("User Location");
            documentReferenceLab = fireStore.collection("Location").document("Lab Location");

            loadData ();


            // BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.buttom_sheet));
          //  bottomSheetBehavior.setPeekHeight(100);
    }


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(31.5130811, 34.4456151);
        MarkerOptions options= new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.client_marker));
        marker_lab=   mMap.addMarker(options);

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);

        /// لازم alert dialoge ليشغل المستخدم الlocayion للتطبيق
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return false;
            }
        });
        mMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {

            }
        });

        //  addCircle();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


    }



    @Override
    public void onMapClick(LatLng latLng) {
        if(marker_lab!= null){
            marker_lab.remove();
        }
        String  info ="any thing ";
        MarkerOptions options= new MarkerOptions()
                .position(latLng)
                .snippet("This is current user location")

                .icon(BitmapDescriptorFactory.fromResource(R.drawable.client_marker));
        marker_lab=   mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        marker_lab.setTag(info);

    }


    public void back_to_home(View view) {
        Intent intent = new Intent (MapsLabrotoryActivity.this , MapsActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    public void confirm(View view) {
        materialCardViewSummery.setVisibility(View.VISIBLE);
        String labStreet = edit_text_lab_location_text.getText().toString();
        TextView text_view_lab_name = findViewById(R.id.text_view_lab_name);
        text_view_lab_name.setText(labStreet);
        if (TextUtils.isEmpty(labStreet)) {

            edit_text_lab_location_text.setError(getString(R.string.required));
        }
        else {
            Map<String, Object> location = new HashMap<>();
            location.put(LAB_LOCATION, labStreet);

            documentReferenceLab.set(location)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MapsLabrotoryActivity.this, "added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MapsLabrotoryActivity.this, "Not added", Toast.LENGTH_SHORT).show();

                }
            });


            con_add.setVisibility(View.GONE);
            cont_sec.setVisibility(View.VISIBLE);
            edit_text_lab_location_text.setVisibility(View.GONE);
            text_view_lab_location.setText(labStreet);
            text_view_lab_location.setVisibility(View.VISIBLE);
            cont_sec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add_time_visit(v);
                }
            });


          //  visible_view();

        }
        }


    public void change_time(View view) {

      // confirm(view);
      //  add_time(view);
        add_time_visit(view);

    }

    private void add_time(View view) {
      //  getActivity().findViewById(R.id.materialCardView).setVisibility(View.VISIBLE);
        materialCardView.setVisibility(View.VISIBLE);


    }

    public void confirm_your_location(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        ImageView imageViewDone = dialog.findViewById(R.id.imageViewDone);
        //image.setImageResource(R.drawable.ic_launcher);
        imageViewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  dialog.dismiss();
                Intent intent = new Intent(MapsLabrotoryActivity.this , UserVisitActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
      dialog.setCancelable(false);


    }


    /*    AlertDialog alertDialog = new AlertDialog.Builder(
                this).create();
        //  alertDialog.setTitle("TITLE"); // your dialog title
        alertDialog.setMessage("Has been requested a home visit succsessfly"); // a message above the buttons
        alertDialog.setIcon(R.drawable.ic_done_all); // the icon besides the title you have to change it to the icon/image you have.
        alertDialog.setButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { // here you can add a method  to the button clicked. you can create another button just by copying alertDialog.setButton("okay")
            }

        });
        alertDialog.show();*/


    public void visible_view (){
      materialCardView.setVisibility(View.VISIBLE);
     confirm_image_view.setVisibility(View.VISIBLE);
     materialCardViewSummery.setVisibility(View.VISIBLE);

    }
   /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "Entered onActivityResult()");
        if(requestCode==GET_POSITION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                text_view_user_location.setText(data.getStringExtra("Location"));

              //  Toast.makeText(this, "Latitute :" +data.getDoubleExtra("Lat" , 0), Toast.LENGTH_SHORT).show();
                // Add a marker in Sydney and move the camera
               LatLng latLng1 = new LatLng(data.getDoubleExtra("Lat" , 0), data.getDoubleExtra("Long", 0));
                MarkerOptions options1= new MarkerOptions()
                        .position(latLng1)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.client_marker));
                marker_user=   mMap.addMarker(options1);

            }
        }


    }*/

   public  void loadData (){

       documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
           @Override
           public void onSuccess(DocumentSnapshot documentSnapshot) {
               String userLocation = documentSnapshot.getString( USER_LOCATION );
               text_view_user_location.setText(userLocation);


           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(MapsLabrotoryActivity.this, "falier to load data", Toast.LENGTH_SHORT).show();
           }
       });


   }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if( requestCode == REQUEST_PICK_USER_LOCATION && resultCode == RESULT_OK){
            Bundle bundle = data.getBundleExtra(MapsActivity.LO);
          double lat =  bundle.putDouble(MapsActivity.LAT);

            mMap.addMarker(new MarkerOptions().position());

        }*/



    }

    public void add_time_visit(View view) {

       ModelSheet modelSheet = new ModelSheet();
        modelSheet.show(getSupportFragmentManager(), modelSheet.getTag());
    }
}
