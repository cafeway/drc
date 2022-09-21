package com.example.e_sports_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_sports_app.data.User;
import com.example.e_sports_app.helpers.DbHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {
    TextInputEditText inputName,inputEmail,inputPhone,inputPassword1,inputPassword2;
    TextInputLayout layoutName,layoutEmail,layoutPhone,layoutPassword1,layoutPassword2;
    Button registerButton;
    TextView openLoginPage;
    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//       initialize input fields
        inputName = findViewById(R.id.textFieldName);
        inputEmail = findViewById(R.id.textFieldEmail);
        inputPassword1 = findViewById(R.id.textFieldPassword1);
        inputPassword2 = findViewById(R.id.textFieldPassword2);
        inputPhone = findViewById(R.id.textFieldPhoneNumber);
        openLoginPage = findViewById(R.id.openLoginPage);

//       Initialize textInputLayouts

        layoutEmail = findViewById(R.id.layoutEmail);
        layoutName = findViewById(R.id.layoutName);
        layoutPhone = findViewById(R.id.layoutPhone);
        layoutPassword1 = findViewById(R.id.layoutPassword1);

//      Initialize registration button

        registerButton = findViewById(R.id.registerBtn);

//        Initialize dbHelper

        DbHelper helper = new DbHelper(getApplicationContext());
//        Set listener to the button
        registerButton.setOnClickListener(v -> {
//                Check if passwords match
            if (inputPassword1.getText().toString().equals(inputPassword2.getText().toString())) {

//                    Pass data to data class and pass data class to function

                User user = new User(inputName.getText().toString(), inputEmail.getText().toString(), inputPhone.getText().toString(), inputPassword1.getText().toString(), "pending", "user");
                helper.registerUser(user);
            } else {
                Toast.makeText(getApplicationContext(), "Passwords do not match!!", Toast.LENGTH_SHORT).show();
            }
        });

//        Open login page if user has registered

        openLoginPage.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
        private boolean validateEmail(){

            // Extract input from EditText
            String emailInput = layoutEmail.getEditText().getText().toString().trim();

            // if the email input field is empty
            if (emailInput.isEmpty()) {
                layoutEmail.setError("Field can not be empty");
                return false;
            }

            // Matching the input email to a predefined email pattern
            else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                layoutEmail.setError("Please enter a valid email address");
                return false;
            } else {
               layoutEmail.setError(null);
                return true;
            }
        }

    private boolean validatePassword() {
        String passwordInput = layoutPassword1.getEditText().getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            layoutPassword1.setError("Field can not be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            layoutPassword1.setError("Password is too weak");
            return false;
        } else {
           layoutPassword1.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        // if the email and password matches, a toast message
        // with email and password is displayed
        String input = "Email: " + layoutEmail.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + layoutPassword1.getEditText().getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }


}