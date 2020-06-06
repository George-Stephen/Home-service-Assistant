package com.moringa.homeservice.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moringa.homeservice.Objects.User;
import com.moringa.homeservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {
    @BindView(R.id.Username)EditText username;
    @BindView(R.id.EmailAddress) EditText EmailAddress;
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
                String Username = username.getText().toString();
                String email = EmailAddress.getText().toString();
                String password = Password.getText().toString();
                User user = new User(Username,email,password);
                Toast.makeText(SignUp.this,"Success",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUp.this, SearchActivity.class);
                intent.putExtra("Username",Username);
                intent.putExtra("Email",email);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });
    }
}
