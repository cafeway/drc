package com.example.e_sports_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_sports_app.data.User;
import com.example.e_sports_app.helpers.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity {
    TextInputEditText inputName,inputEmail,inputPhone,inputPassword1,inputPassword2;
    Button registerButton;
    TextView openLoginPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//       initialize input fields
        inputName=findViewById(R.id.textFieldName);
        inputEmail=findViewById(R.id.textFieldEmail);
        inputPassword1=findViewById(R.id.textFieldPassword1);
        inputPassword2=findViewById(R.id.textFieldPassword2);
        inputPhone=findViewById(R.id.textFieldPhoneNumber);
        openLoginPage = findViewById(R.id.openLoginPage);

//      Initialize registration button

        registerButton=findViewById(R.id.registerBtn);

//        Initialize dbHelper
        val id = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        DbHelper helper = new DbHelper(getApplicationContext());
        List<String> items= new ArrayList<>();
        ArrayAdapter arrayAdapter= new ArrayAdapter(getApplicationContext(),R.layout.list_item_usertype,items);
//        Set listener to the button
        registerButton.setOnClickListener(v -> {
//                Check if passwords match
            if(inputPassword1.getText().toString().equals(inputPassword2.getText().toString())) {

//                    Pass data to data class and pass data class to function

                User user = new User(inputName.getText().toString(), inputEmail.getText().toString(),inputPhone.getText().toString(), inputPassword1.getText().toString(),"pending","user");
                helper.registerUser(user);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Passwords do not match!!", Toast.LENGTH_SHORT).show();
            }
        });

//        Open login page if user has registered

    openLoginPage.setOnClickListener(v -> {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    });
    }
}