package com.example.labmedixapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.labmedixapplication.R;
import com.example.labmedixapplication.adapter.CustomAdapterSpinner;
import com.example.labmedixapplication.fragment.CheakPhoneNoFragment;
import com.example.labmedixapplication.fragment.CheakPhoneUserFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity   {

    private final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";


    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;

    private FirebaseAuth mAuth;
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    FirebaseDatabase database;
    DatabaseReference myRef;



    Spinner spinner_list;
    CustomAdapterSpinner customAdapterSpinner;
    CountryCodePicker ccp;
    RadioButton radio_button_user , radio_button_laboratory ;
    EditText edit_text_phone_no;
    ImageView to_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        radio_button_user = findViewById(R.id.radio_button_user);
        radio_button_laboratory = findViewById(R.id.radio_button_laboratory);
        edit_text_phone_no=findViewById(R.id.edit_text_phone_no);
        String phone_no = edit_text_phone_no.getText().toString();
        String code= ccp.getSelectedCountryCodeWithPlus().toString();
        String phone = (phone_no+code ).toString();




     /*   mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("db-name");
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

        to_start.setOnClickListener(this);*/




        //   String[] country_code = {"+972", "+93", "+353", "+1684", "+91", "+972", "+61"};
     //   int[] country_flag = {R.drawable.flag_palestine, R.drawable.flag_afghanistan, R.drawable.flag_aland, R.drawable.flag_american_samoa, R.drawable.flag_india, R.drawable.flag_israel, R.drawable.flag_australia};
        ccp = (CountryCodePicker) findViewById(R.id.ccp);

       // ccp.getDefaultCountryCode("+970");

        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                Toast.makeText(RegisterActivity.this, ccp.getDefaultCountryCode() + ccp.getSelectedCountryName(), Toast.LENGTH_SHORT).show();
            }

            // customAdapterSpinner = new CustomAdapterSpinner(this,country_flag , country_code);

            //  spinner_list.setAdapter(customAdapterSpinner);


        /* spinner_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Intent intent= new Intent(RegisterActivity.this , CheakPhoneNoActivity.class);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        });




     /*   mAuth = FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                mVerificationInProgress = false;

              // updateUI(STATE_VERIFY_SUCCESS, credential);

              //  signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

//                Log.e(TAG, "onVerificationFailed", e);
                mVerificationInProgress = false;

                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    edit_text_phone_no.setError(getString(R.string.invalid_phone_number));

                } else if (e instanceof FirebaseTooManyRequestsException) {

                //    Snackbar.make(findViewById(android.R.id.content), R.string.quota_exceeded,
                    //        Snackbar.LENGTH_SHORT).show();
                }

             //   updateUI(STATE_VERIFY_FAILED);
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                mVerificationId = verificationId;
                mResendToken = token;

               // updateUI(STATE_CODE_SENT);
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);

    //    if (mVerificationInProgress && validatePhoneNumber()) {
     //      startPhoneNumberVerification(mPhoneNumberField.getText().toString().trim());
        }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
      //  outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        try {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks

            mVerificationInProgress = true;
        } catch (Exception e) {
//            Log.e(TAG, "startPhoneNumberVerification: " + e.getMessage());
        }
    }



    public void start_with_phone(View view) {

        if(radio_button_user .isChecked()){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new CheakPhoneUserFragment()).commit();

        }
        else {
         getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new CheakPhoneNoFragment()).commit();

        }
    }

    public void back(View view) {
        Intent intent_back = new Intent(RegisterActivity.this , OverViewActivity.class);
        startActivity(intent_back);
    }







    @Override
    public void onClick(View v) {*/

    }
}
