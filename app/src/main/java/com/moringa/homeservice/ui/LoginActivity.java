package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moringa.homeservice.Objects.Login;
import com.moringa.homeservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
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
                Intent intent = new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginEmail == null || LoginPassword == null) {
                    Toast.makeText(LoginActivity.this,"Please enter the details required",Toast.LENGTH_LONG).show();
                } else {
                    String email = LoginEmail.getText().toString();
                    String password = LoginPassword.getText().toString();
                    Login login = new Login(email, password);
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });
    }
}
