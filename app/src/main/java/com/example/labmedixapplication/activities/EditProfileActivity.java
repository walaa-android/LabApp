package com.example.labmedixapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.labmedixapplication.R;
import com.example.labmedixapplication.fragment.MyDatePickerFragment;
import com.example.labmedixapplication.fragment.ProfileFragment;
import com.example.labmedixapplication.fragment.UserInformationFragment;
import com.example.labmedixapplication.model.ModelAddGender;
import com.example.labmedixapplication.model.ModelSheet;
import com.example.labmedixapplication.model.ModelSheetEditEmail;
import com.example.labmedixapplication.model.ModelSheetEditGender;
import com.example.labmedixapplication.model.ModelSheetEditName;
import com.example.labmedixapplication.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.UUID;

public class EditProfileActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_IMAGE = 100;
    private Uri imageUri ;
    CircleImageView userImageView;
    DatabaseReference informationRef;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView textViewName , textViewPhone , textViewEmail , text_view_birth , text_view_gender;
    String currentUserid;
    StorageReference mainRef ;
    Uri filePath;
    FirebaseStorage storage;
    String photoUrl;
    StorageReference storageReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        userImageView = findViewById(R.id.image_view_user);
        textViewName= findViewById(R.id.text_view_name);
        textViewPhone = findViewById(R.id.text_view_phone);
        textViewEmail = findViewById(R.id.text_view_email);
        text_view_birth = findViewById(R.id.text_view_birth);
        text_view_gender =findViewById(R.id.text_view_gender);

        informationRef = FirebaseDatabase.getInstance().getReference("userInformation");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        currentUserid=currentUser.getUid();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        text_view_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelSheetEditGender modelSheetEditGender = new ModelSheetEditGender();

                modelSheetEditGender.show(getSupportFragmentManager(), modelSheetEditGender.getTag());

            }
        });


        textViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelSheetEditEmail modelSheetEditEmail = new ModelSheetEditEmail();

                modelSheetEditEmail.show(getSupportFragmentManager(), modelSheetEditEmail.getTag());

            }
        });
        sharedPreferences = getSharedPreferences("My Login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModelSheetEditName modelSheetEditName = new ModelSheetEditName();

                modelSheetEditName.show(getSupportFragmentManager(), modelSheetEditName.getTag());

            }
        });


    }

    public void edit_photo(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent ,REQUEST_PICK_IMAGE);





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data!= null && data.getData() != null){
            imageUri = data.getData();
            if (imageUri != null){
              //  userImageView.setImageURI(imageUri);


             //   informationRef = FirebaseDatabase.getInstance().getReference("userInformation")
                 //       .child(FirebaseAuth.getInstance().getUid());
        //    informationRef.child("photo").setValue(userImageView);

                    filePath = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        userImageView.setImageBitmap(bitmap);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

            }
        }

        uploadImage();
    }

    @Override
    public void onStart() {


        informationRef = FirebaseDatabase.getInstance().getReference("userInformation");
        informationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel fullName = dataSnapshot.child(FirebaseAuth.getInstance().getUid()).getValue(UserModel.class);
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String currentUserid = currentUser.getUid();
                //     String idUser=dataSnapshot.child(FirebaseAuth.getInstance().getUid()).getValue(String.class);

                assert fullName != null;
                //    if (idUser.equals(currentUserid)) {
                textViewName.setText(fullName.getFullName());
                textViewPhone.setText(fullName.getPhoneNo());
                textViewEmail.setText(fullName.getUserEmail());
                text_view_birth.setText(fullName.getBirth());
                text_view_gender.setText(fullName.getGender());
                String uploadImage=dataSnapshot.child("mImageUrl").getValue(String.class);
                userImageView.setImageURI(imageUri);





                //     userImageView.setImageURI(imageUri);

            }
            //   String uploadImage=snapshot.child("mImageUrl").getValue(String.class);

            //  userImageView.setImageURI(imageUri);
            //     userImageView.setImageURI((fullName.getmImageUrlPohto()));
            /////////////////////////// ٍ  Save photo  in to real time ////////////////////////

            //    Glide.with(EditProfileActivity.this).load(sharedPreferences
            //        .getString("url","https://console.firebase.google.com/u/1/project/lab-medix-final-project/storage/lab-medix-final-project.appspot.com/files~2FImage")).into(userImageView);




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        super.onStart();
    }





    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_add_gender);
      final DatePicker datePicker=(DatePicker)findViewById(R.id.datePicker);
       ImageView save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditProfileActivity.this, "\"Selected Date: \"+ datePicker.getDayOfMonth()+\"/\"+ (datePicker.getMonth() + 1)+\"/\"+datePicker.getYear()", Toast.LENGTH_SHORT).show();
             
            }
        });
    }*/


    public void add_date_of_birth(View view) {


       // ModelAddGender modelAddGender= new ModelAddGender();

      //  modelAddGender.show(getSupportFragmentManager(), modelAddGender.getTag());

        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }



    private void uploadImages() {

        if(filePath != null)
        {


            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            ///    Glide.with(EditProfileActivity.this).load(uri).into(userImageView);

                               /// editor.putString("url",uri.toString());
                            editor.apply();


                        }
                    });


                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(MainActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                        }
                    });


           final StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child(System.currentTimeMillis() + "." + getFileExtensition(imageUri));
                   storageReference1 .putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                photoUrl= uri.toString();
                            }
                        });
                }
            });

                }


        }

    private String  getFileExtensition(Uri imageUri) {
       return  MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(imageUri));

    }


    private void uploadImage() {


        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(EditProfileActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(EditProfileActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
        uploadImages();
    }

    public void back(View view) {

      //  Intent intent = new Intent( EditProfileActivity.this , ProfileFragment.class);
      //  startActivity(intent);
      //  getSupportFragmentManager().beginTransaction().replace(R.id.prof ,new ProfileFragment()).commit();

    }
}
