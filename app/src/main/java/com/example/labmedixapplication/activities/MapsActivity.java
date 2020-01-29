package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.model.LocationModel;
import com.example.labmedixapplication.model.UserModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.DescriptorProtos;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener , GoogleMap.OnMarkerDragListener , GoogleMap.OnInfoWindowClickListener {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private static final int REQUEST_PICK_USER_LOCATION = 101;
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String LOCATION = "location";
    private GoogleMap mMap;
    private Marker marker;
    EditText edit_text_street_location;
    DatabaseReference informationRef;
    SharedPreferences.Editor editor;
    FirebaseFirestore fireStore;
    DocumentReference documentReference;
     private static  final String USER_LOCATION = "userlocation";
     String LatLongUserCurrentLocation;
    static private final String TAG = MapsActivity.class.getSimpleName();
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fireStore = FirebaseFirestore.getInstance();
        documentReference = fireStore.collection("Location").document("User Location");

      /*  GoogleMapOptions options = new GoogleMapOptions();
        options.compassEnabled(true)
                .rotateGesturesEnabled(true);*/


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        edit_text_street_location = findViewById(R.id.edit_text_street_location);


        if (mapFragment != null) {
            mapFragment.getMapAsync(this);


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
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.client_marker));
        marker = mMap.addMarker(options);

        new AlertDialog.Builder(this)
                .setTitle(R.string.title_location_permission)
                .setMessage(R.string.text_location_permission)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(MapsActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
                    }
                })
                .create()
                .show();
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnInfoWindowClickListener(this);


        mMap.setMyLocationEnabled(true);



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

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


        bundle = new Bundle();
        bundle.putDouble(LATITUDE,latLng.latitude );
        bundle.putDouble(LONGITUDE ,latLng.latitude );

        LatLongUserCurrentLocation = latLng.latitude +" , " + latLng.longitude;


        //LatLongUserCurrentLocation= marker;
        Toast.makeText(MapsActivity.this,  " " + LatLongUserCurrentLocation, Toast.LENGTH_SHORT).show();


    }



    @Override
    public void onMapClick(LatLng latLng) {
        if (marker != null) {
            marker.remove();
        }
        String info = "any thing ";
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .snippet("This is current user location")

                .icon(BitmapDescriptorFactory.fromResource(R.drawable.client_marker));

        marker = mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        marker.setTag(info);

          bundle = new Bundle();
          bundle.putDouble(LATITUDE,latLng.latitude );
         bundle.putDouble(LONGITUDE ,latLng.latitude );
         LatLongUserCurrentLocation = latLng.latitude +" , " + latLng.longitude;


        //LatLongUserCurrentLocation= marker;
        Toast.makeText(MapsActivity.this,  " " + LatLongUserCurrentLocation, Toast.LENGTH_SHORT).show();


    }

    String info = "any thing ";


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, (String) marker.getTag(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(this, marker.getPosition().latitude + " " + marker.getPosition().longitude + " ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
// after select position go to other activity for example
    }

    public void back_to_home(View view) {
        Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
        startActivity(intent);

    }

    public void confirm(View view) {

        if (TextUtils.isEmpty(edit_text_street_location.getText())) {
            edit_text_street_location.setError(getString(R.string.required));
        } else {
            saveLocation(view);
            Intent intent = new Intent(MapsActivity.this, MapsLabrotoryActivity.class);

            startActivityForResult(intent ,REQUEST_PICK_USER_LOCATION  );

           // startActivity(intent);
        }


    }

    private void enterClicked() {
        int GET_POSITION_REQUEST_CODE = 1;


        Log.i(TAG, "Entered enterClicked()");


        //   double locationLat = marker.getPosition().latitude;
        //  double locationLong = marker.getPosition().longitude;

        String userStreet = edit_text_street_location.getText().toString();
        if (!TextUtils.isEmpty(userStreet)) {
            //  String id=  informationRef.push().getKey();
            //    int  phone = (informationRef.child(FirebaseAuth.getInstance().getUid()).child("phoneNumber"));
          //  LocationModel locationModel = new LocationModel(userStreet);
            UserModel userModel = new UserModel(userStreet);
         //  informationRef.child("users").push() .setValue(userModel);

          informationRef.child(userStreet).setValue(userModel);
            editor.putString("userLocation", userStreet);
            editor.apply();
      /*  Intent explicit_intent = new Intent(MapsActivity.this , MapsLabrotoryActivity.class);
        explicit_intent.putExtra("Location", street);
      //  explicit_intent.putExtra("Lat",locationLat );
      //  explicit_intent.putExtra("Long", locationLong);
        setResult(RESULT_OK,explicit_intent);
        finish();*/

        }

    }
    public void saveLocation (View v){
        String userStreet = edit_text_street_location.getText().toString();


        if(bundle != null){
            Intent intent = new Intent();
            intent.putExtra(LOCATION, bundle);
            setResult(RESULT_OK , intent);

        }
        finish();

        if (!TextUtils.isEmpty(userStreet)) {

            Map <String , Object > location = new HashMap<>();
            location.put(USER_LOCATION , userStreet);
            documentReference.set(location)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MapsActivity.this, "added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MapsActivity.this, "Not added", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

}
