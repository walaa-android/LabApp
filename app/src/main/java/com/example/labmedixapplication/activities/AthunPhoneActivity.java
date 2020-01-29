     package com.example.labmedixapplication.activities;

     import androidx.annotation.NonNull;
     import androidx.appcompat.app.AppCompatActivity;

     import android.content.Intent;
     import android.os.Bundle;
     import android.text.TextUtils;
     import android.view.View;
     import android.view.ViewGroup;
     import android.widget.Button;
     import android.widget.EditText;
     import android.widget.ImageView;
     import android.widget.RadioButton;
     import android.widget.RadioGroup;
     import android.widget.TextView;
     import android.widget.Toast;

     import com.example.labmedixapplication.R;
     import com.example.labmedixapplication.fragment.UserInformationFragment;
     import com.google.android.gms.tasks.OnCompleteListener;
     import com.google.android.gms.tasks.Task;
     import com.google.android.material.snackbar.Snackbar;
     import com.google.firebase.FirebaseException;
     import com.google.firebase.FirebaseTooManyRequestsException;
     import com.google.firebase.auth.AuthResult;
     import com.google.firebase.auth.FirebaseAuth;
     import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
     import com.google.firebase.auth.FirebaseUser;
     import com.google.firebase.auth.PhoneAuthCredential;
     import com.google.firebase.auth.PhoneAuthProvider;
     import com.google.firebase.database.DatabaseReference;
     import com.google.firebase.database.FirebaseDatabase;


     import java.util.Locale;
     import java.util.concurrent.TimeUnit;

     public class AthunPhoneActivity extends AppCompatActivity implements
             View.OnClickListener {

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
         private ViewGroup mPhoneNumberViews;
         private ViewGroup mSignedInViews;
         private TextView mDetailText;
         private EditText mPhoneNumberField;
         private EditText mVerificationField;
         RadioButton radioButtonUser , radioButtonLab;
         RadioGroup radio_group;
         TextView textView;



            private Button mStartButton;
            private Button mVerifyButton;
            private Button mResendButton;
             private Button mSignOutButton;

             FirebaseDatabase database;
             DatabaseReference myRef;
         DatabaseReference informationRef;


         @Override
             protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_athun_phone);
                 radio_group = findViewById(R.id.radio_group);
                 radioButtonUser = findViewById(R.id.radio_button_user);
                 radioButtonLab=findViewById(R.id.radio_button_laboratory);
                 textView=findViewById(R.id.textView);

     //        Toolbar mTopToolbar = findViewById(R.id.toolbar);
     //        setSupportActionBar(mTopToolbar);


         ImageView image_view_back = findViewById(R.id.image_view_back);
         image_view_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(AthunPhoneActivity.this , OverViewActivity.class);
                 startActivity(intent);
             }
         });


                 mAuth = FirebaseAuth.getInstance();
             database = FirebaseDatabase.getInstance();
         //    myRef = database.getReference("db-name");
             informationRef = FirebaseDatabase.getInstance().getReference("userInformation");

             String language = Locale.getDefault().getDisplayLanguage().toLowerCase();

             if (language.equals("العربية"))
             mAuth.setLanguageCode("ar");
             else if (language.equals("english"))
             mAuth.setLanguageCode("en");

             if (savedInstanceState != null) {
             onRestoreInstanceState(savedInstanceState);
             }


             mPhoneNumberViews = findViewById(R.id.phoneAuthFields);
             mSignedInViews = findViewById(R.id.signedInButtons);

             mDetailText = findViewById(R.id.detail);

             mPhoneNumberField = findViewById(R.id.fieldPhoneNumber);
             mVerificationField = findViewById(R.id.fieldVerificationCode);

             mPhoneNumberField.setSelection(mPhoneNumberField.getText().length());

             mStartButton = findViewById(R.id.buttonStartVerification);
             mVerifyButton = findViewById(R.id.buttonVerifyPhone);
             mResendButton = findViewById(R.id.buttonResend);
             mSignOutButton = findViewById(R.id.signOutButton);

             mStartButton.setOnClickListener(this);
             mVerifyButton.setOnClickListener(this);
             mResendButton.setOnClickListener(this);
             mSignOutButton.setOnClickListener(this);
             radioButtonUser.setOnClickListener(this);
             radioButtonLab.setOnClickListener(this);




             mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

             mVerificationInProgress = false;

             updateUI(STATE_VERIFY_SUCCESS, credential);

             signInWithPhoneAuthCredential(credential);
             }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                   //                Log.e(TAG, "onVerificationFailed", e);
             mVerificationInProgress = false;

             if (e instanceof FirebaseAuthInvalidCredentialsException) {

             mPhoneNumberField.setError(getString(R.string.invalid_phone_number));

             } else if (e instanceof FirebaseTooManyRequestsException) {
         //   Snackbar.make(findViewById(android.R.id.content), R.string.quota_exceeded,
         //   Snackbar.LENGTH_SHORT).show();
             }

             updateUI(STATE_VERIFY_FAILED);
             }

             @Override
             public void onCodeSent(String verificationId,
             PhoneAuthProvider.ForceResendingToken token) {

             mVerificationId = verificationId;
             mResendToken = token;

             updateUI(STATE_CODE_SENT);
             }
             };
             }

             @Override
             public void onStart() {
             super.onStart();
             FirebaseUser currentUser = mAuth.getCurrentUser();
             updateUI(currentUser);

             if (mVerificationInProgress && validatePhoneNumber()) {
             startPhoneNumberVerification(mPhoneNumberField.getText().toString().trim());
             }
             }

             @Override
             protected void onSaveInstanceState(Bundle outState) {
             super.onSaveInstanceState(outState);
             outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
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

     private void verifyPhoneNumberWithCode(String verificationId, String code) {
             PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
             signInWithPhoneAuthCredential(credential);
             }

     private void resendVerificationCode(String phoneNumber,
             PhoneAuthProvider.ForceResendingToken token) {
             PhoneAuthProvider.getInstance().verifyPhoneNumber(
             phoneNumber,        // Phone number to verify
             60,                 // Timeout duration
             TimeUnit.SECONDS,   // Unit of timeout
             this,               // Activity (for callback binding)e
             mCallbacks,         // OnVerificationStateChangedCallbacks
             token);             // ForceResendingToken from callbacks
             }

     private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
             mAuth.signInWithCredential(credential)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
     @Override
     public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()) {

             FirebaseUser user = task.getResult().getUser();

             try {
                 ///////////////////////// add phone no to database//////////////////////
                 informationRef.child("informationRef").child(user.getUid()).child("phoneNumber").setValue(user.getPhoneNumber());


               informationRef.child("users").child(user.getUid()).child("phoneNumber").setValue(user.getPhoneNumber());
             } catch (Exception e) {
             }

             updateUI(STATE_SIGNIN_SUCCESS, user, credential);

             } else {
             if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
             mVerificationField.setError(getString(R.string.invalid_code));
             }
             updateUI(STATE_SIGNIN_FAILED);
             }
             }
             });
             }

             private void signOut() {
             mAuth.signOut();
             updateUI(STATE_INITIALIZED);
             }

             private void updateUI(int uiState) {
             updateUI(uiState, mAuth.getCurrentUser(), null);
             }

             private void updateUI(FirebaseUser user) {
             if (user != null) {
             updateUI(STATE_SIGNIN_SUCCESS, user);
             } else {
             updateUI(STATE_INITIALIZED);
             }
             }
             private void updateUI(int uiState, FirebaseUser user) {
             updateUI(uiState, user, null);
             }

             private void updateUI(int uiState, PhoneAuthCredential cred) {
             updateUI(uiState, null, cred);
             }

            private void updateUI(int uiState, FirebaseUser user, PhoneAuthCredential cred) {
             switch (uiState) {
                 case STATE_INITIALIZED:
                     enableViews(mStartButton, mPhoneNumberField);
                     mVerifyButton.setVisibility(View.GONE);
                     mResendButton.setVisibility(View.GONE);
                     mStartButton.setVisibility(View.VISIBLE);

                     mVerificationField.setVisibility(View.GONE);
                     disableViews(mVerifyButton, mResendButton, mVerificationField);
                     break;

                 case STATE_CODE_SENT:
                     enableViews(mVerifyButton, mResendButton, mPhoneNumberField, mVerificationField);
                     disableViews(mStartButton);
                     mVerifyButton.setVisibility(View.VISIBLE);
                     mResendButton.setVisibility(View.VISIBLE);
                     mStartButton.setVisibility(View.GONE);
                     mVerificationField.setVisibility(View.VISIBLE);
                     mDetailText.setText(R.string.code_sent);
                     ////////////////////////////////////////////// added
                     radioButtonLab.setVisibility(View.GONE);
                     radioButtonUser.setVisibility(View.GONE);
                     textView.setVisibility(View.GONE);

                     break;

                 case STATE_VERIFY_FAILED:
                     ////////////////////////////////////////////// added
                     radioButtonLab.setVisibility(View.GONE);
                     radioButtonUser.setVisibility(View.GONE);
                     radio_group.setVisibility(View.GONE);
                     textView.setVisibility(View.GONE);
                     enableViews(mStartButton, mVerifyButton, mResendButton, mPhoneNumberField,
                             mVerificationField);
                     mVerifyButton.setVisibility(View.GONE);
                     mResendButton.setVisibility(View.VISIBLE);
                     mStartButton.setVisibility(View.VISIBLE);
                     mVerificationField.setVisibility(View.VISIBLE);
                     mDetailText.setText(R.string.verification_failed);
                     break;

                 case STATE_VERIFY_SUCCESS:
                     ////////////////////////////////////////////// added
                     radioButtonLab.setVisibility(View.GONE);
                     radioButtonUser.setVisibility(View.GONE);
                     radio_group.setVisibility(View.GONE);
                     textView.setVisibility(View.GONE);
                     disableViews(mStartButton, mVerifyButton, mResendButton, mPhoneNumberField,
                             mVerificationField);

                     mDetailText.setText(R.string.verification_succeeded);
                     mVerificationField.setVisibility(View.GONE);


                     if (cred != null) {
                         if (cred.getSmsCode() != null) {
                             mVerificationField.setText(cred.getSmsCode());
                         } else {
                             mVerificationField.setText(R.string.instant_validation);
                         }
                     }
                     break;

                 case STATE_SIGNIN_FAILED:
                     mDetailText.setText(R.string.signed_in_failed);
                     break;

                 case STATE_SIGNIN_SUCCESS:
                   // Toast.makeText(this, "verifed completed success", Toast.LENGTH_LONG).show();
                 /* if (radioButtonLab.isChecked()) {
                         startActivity(new Intent(AthunPhoneActivity.this, LabInformationActivity.class));
                         finish();
                     } else {*/
                      startActivity(new Intent(AthunPhoneActivity.this, UserInformationActivity.class));
                      finish();

              //    }

                     break;
             }
             if (user == null) {
             mPhoneNumberViews.setVisibility(View.VISIBLE);
             mSignedInViews.setVisibility(View.GONE);

             } else {
             mPhoneNumberViews.setVisibility(View.GONE);
             mSignedInViews.setVisibility(View.VISIBLE);

             enableViews(mPhoneNumberField, mVerificationField);
             mPhoneNumberField.setText(null);
             mVerificationField.setText(null);

             }
             }

     private boolean validatePhoneNumber() {
             String phoneNumber = mPhoneNumberField.getText().toString().trim();
             if (TextUtils.isEmpty(phoneNumber)) {
             mPhoneNumberField.setError(getString(R.string.invalid_phone_number));
             return false;
             }

             return true;
             }

     private void enableViews(View... views) {
             for (View v : views) {
             v.setEnabled(true);
             }
             }

     private void disableViews(View... views) {
             for (View v : views) {
             v.setEnabled(false);
             }
             }

     @Override
     public void onClick(View view) {
             switch (view.getId()) {
             case R.id.buttonStartVerification:
             if (!validatePhoneNumber()) {
             return;
             }

             startPhoneNumberVerification(mPhoneNumberField.getText().toString().trim());
             break;

             case R.id.buttonVerifyPhone:
             String code = mVerificationField.getText().toString().trim();
             if (TextUtils.isEmpty(code)) {
             return;
             }
             verifyPhoneNumberWithCode(mVerificationId, code);
             break;

             case R.id.buttonResend:
             resendVerificationCode(mPhoneNumberField.getText().toString().trim(), mResendToken);
             break;

             case R.id.signOutButton:
             signOut();
             break;

             case R.id.buttonSignIn:
             break;
             }
             }
     }
