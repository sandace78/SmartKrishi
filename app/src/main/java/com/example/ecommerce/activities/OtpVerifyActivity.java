package com.example.ecommerce.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ecommerce.R;
import com.example.ecommerce.helpers.AppActivity;
import com.example.ecommerce.models.Customers;
import com.example.ecommerce.presenters.CustomerPresenters;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.GsonBuilder;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.util.concurrent.TimeUnit;


public class OtpVerifyActivity extends AppActivity implements View.OnClickListener, OnOtpCompletionListener, CustomerPresenters.View {
private Button btn_submit;
private OtpView otpView;
    private  String mVerificationId;
    private  String id,number;
    double lat ,lan;



    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;
    private String phoneNumber="+9779843618859";
    private String code="123456";
    CustomerPresenters customerPresenters;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);
        initializedView();
        initializedListners();


    }

    @Override
    protected void initializedView() {
        btn_submit=findViewById(R.id.btn_submit_verifyotp);
        otpView=findViewById(R.id.otp_view);
        number=getIntent().getStringExtra("numbers");
        Log.e( "initializedView: ",number);
        lat=getIntent().getDoubleExtra("lat",0);
        lan=getIntent().getDoubleExtra("lon",0);
        mAuth=FirebaseAuth.getInstance();

        sendOtp();
//sendTestOtp();

    }

    @Override
    protected void initializedListners() {
        btn_submit.setOnClickListener(this);
        otpView.setOtpCompletionListener(this);
        customerPresenters=new CustomerPresenters(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_verifyotp:
                try {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, String.valueOf(otpView.getText()));
                    signInWithPhoneAuthCredential(credential);
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), "Verification Code is wrong, try again", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                }



                break;
        }
    }

    @Override
    public void onOtpCompleted(String otp) {
        Log.e( "onOtpCompleted: ", "called");

        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, otp);
            signInWithPhoneAuthCredential(credential);
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Verification Code is wrong, try again", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.e( "onCompleted:"  ,""+credential);

            signInWithPhoneAuthCredential(credential);

        }



        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.e("onVerificationFailed", ""+e);

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
            }

            // Show a message and update the UI
            // ...
        }


        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.e( "onCodeSent:" , ""+verificationId);

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            mResendToken = token;

            // ...
        }
    };

    private void sendOtp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+977"+number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, String.valueOf(mResendToken));

    }
    //this is for not send code to actual number but for testing
    private void sendTestOtp(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Log.e( "onCompleted:"  ,""+phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.e("onVerificationFailed", ""+e);

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                       id=s;
                    }
                });        // OnVerificationStateChangedCallbacks

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e( "signIn","WithCredential:success");
                                customerPresenters.customer(number,lat,lan);

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.e("signIn:failure", ""+task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    @Override
    public void onCustomerResponseSuccess(Customers customers) {
        Log.e( "onCustomerSuccess: ",new GsonBuilder().create().toJson(customers));

        Intent intent=new Intent(OtpVerifyActivity.this,Dashboard.class);
        startActivity(intent);
    }

    @Override
    public void onCustomerResponseFailure(String message) {

    }
}
