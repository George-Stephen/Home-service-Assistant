package com.moringa.homeservice.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.moringa.homeservice.Objects.User;
import com.moringa.homeservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {
    private static final String TAG = SignUp.class.getSimpleName();
    @BindView(R.id.Username)EditText username;
    @BindView(R.id.EmailAddress) EditText EmailAddress;
    @BindView(R.id.password)EditText Password;
    @BindView(R.id.ConfirmPassword)EditText ConfirmPassword;
    @BindView(R.id.signUp) Button SignButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        SignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }
    private void createUser(){
        String Username = username.getText().toString();
        String email = EmailAddress.getText().toString();
        String password = Password.getText().toString();
        String Confirm = ConfirmPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Authentication success",Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Authentication successful");
                    Intent intent = new Intent(SignUp.this, SearchActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(SignUp.this,"Authentication failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
