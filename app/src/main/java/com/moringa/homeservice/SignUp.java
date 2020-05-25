package com.moringa.homeservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {
    @BindView(R.id.fullName) EditText FullName;
    @BindView(R.id.Username)EditText username;
    @BindView(R.id.EmailAddress) EditText EmailAddress;
    @BindView(R.id.Phone)EditText Phone;
    @BindView(R.id.password)EditText Password;
    @BindView(R.id.signUp) Button SignButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        SignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = FullName.getText().toString();
                String Username = username.getText().toString();
                String Email = EmailAddress.getText().toString();
                String phone = Phone.getText().toString();
                String password = Password.getText().toString();
                Toast.makeText(SignUp.this,"Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUp.this,Search.class);
                intent.putExtra("fullName",fullName);
                intent.putExtra("Username",Username);
                intent.putExtra("Email",Email);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });
    }
}
