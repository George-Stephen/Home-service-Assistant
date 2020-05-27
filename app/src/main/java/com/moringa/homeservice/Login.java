package com.moringa.homeservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {
    @BindView(R.id.emailAddress) EditText LoginEmail;
    @BindView(R.id.loginPassword) EditText LoginPassword;
    @BindView(R.id.loginButton) Button LoginButton;
    @BindView(R.id.et_signUp) TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = LoginEmail.getText().toString();
                String password  = LoginPassword.getText().toString();
                Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this,Search.class);
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });
    }
}
