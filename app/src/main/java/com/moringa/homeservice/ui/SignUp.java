package com.moringa.homeservice.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringa.homeservice.Objects.User;
import com.moringa.homeservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {
    private static final String TAG = SignUp.class.getSimpleName();
    @BindView(R.id.Username)EditText username;
    @BindView(R.id.EmailAddress) EditText EmailAddress;
    @BindView(R.id.password)EditText mPassword;
    @BindView(R.id.ConfirmPassword)EditText ConfirmPassword;
    @BindView(R.id.signUp) Button SignButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgress;
    private String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();




        SignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }
    private void createUser(){
         Username = username.getText().toString();
        String email = EmailAddress.getText().toString();
        String password = mPassword.getText().toString();
        String Confirm = ConfirmPassword.getText().toString();
        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(Username);
        boolean validPassword = isValidPassword(password,Confirm);
        if (!validEmail || !validName || ! validPassword) return;

        mAuthProgress.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgress.dismiss();


                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Authentication success",Toast.LENGTH_LONG).show();

                    Log.d(TAG, "Authentication successful");

                    createFirebaseUserProfile(task.getResult().getUser());
                } else{
                    Toast.makeText(SignUp.this,"Authentication failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createFirebaseUserProfile(final FirebaseUser user){
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(Username)
                .build();
        user.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "" + user.getDisplayName());
                }
            }
        });
    }
    private void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Intent intent = new Intent(SignUp.this,SearchActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            EmailAddress.setError("Please enter a valid email Address");
            return false;
        }
        return true;
    }
    private boolean isValidName(String name){
        if (name.equals("")){
            username.setError("please  enter your name");
            return false;
        }
        return true;
    }
    private boolean isValidPassword(String Password,String confirmPassword){
        if (Password.length() < 6) {
            mPassword.setError("The password should be at least 6 Characters");
            return false;

        }else if(!Password.equals(confirmPassword)){
            mPassword.setError("Passwords don't match");
            return false;
        }
        return true;
    }

    private void createAuthProgressDialog(){
        mAuthProgress  = new ProgressDialog(this);
        mAuthProgress.setTitle("Loading...");
        mAuthProgress.setMessage("Creating your account....");
        mAuthProgress.setCancelable(false);
    }
}
