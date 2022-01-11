package com.example.e_sports_app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_sports_app.helpers.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends Activity {

    TextInputEditText inputEmail, inputPassword;
    TextView openReg;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        The text input fields
        inputEmail = findViewById(R.id.textFieldEmail);
        inputPassword = findViewById(R.id.textFieldPassword);

//        Textfield to open tht register page
        openReg = findViewById(R.id.openRegisterPage);

//        Initialize login btn
        loginButton = findViewById(R.id.loginBtn);

//        Initialize th db helper
        DbHelper helper = new DbHelper(this);

//        Set the listener for the login button

        loginButton.setOnClickListener(v->{

//            Calling the login function
            helper.loginUser(inputEmail.getText().toString(),inputPassword.getText().toString());
        });
    }
}