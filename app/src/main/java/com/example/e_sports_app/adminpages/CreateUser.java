package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.User;
import com.example.e_sports_app.helpers.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class CreateUser extends Activity implements AdapterView.OnItemSelectedListener {
    TextInputEditText inputName,inputEmail,inputPhone,inputPassword1,inputPassword2;
    Button registerButton;
    TextView openLoginPage;
    Spinner spinner;
    String selected_user_type;
    Toolbar toolbar;
    String[] user_type = { "Admin", "Captain", "Facilitator"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //       initialize input fields
        inputName=findViewById(R.id.textFieldName);
        inputEmail=findViewById(R.id.textFieldEmail);
        inputPassword1=findViewById(R.id.textFieldPassword1);
        inputPassword2=findViewById(R.id.textFieldPassword2);
        inputPhone=findViewById(R.id.textFieldPhoneNumber);
        openLoginPage = findViewById(R.id.openLoginPage);
        spinner = findViewById(R.id.account_type);
        toolbar = findViewById(R.id.toolBar);
//      Initialize registration button

        registerButton=findViewById(R.id.registerBtn);

        setActionBar(toolbar);
        getActionBar().setTitle("Manage Users");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);


//        Initializing listener for spinner

        spinner.setOnItemSelectedListener(this);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,user_type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        //        Initialize dbHelper

        DbHelper helper = new DbHelper(getApplicationContext());

        //        Set listener to the button
        registerButton.setOnClickListener(v -> {
//                Check if passwords match
            if(inputPassword1.getText().toString().equals(inputPassword2.getText().toString())) {

//                    Pass data to data class and pass data class to function

                User user = new User(inputName.getText().toString(), inputEmail.getText().toString(),inputPhone.getText().toString(), inputPassword1.getText().toString(),"pending",selected_user_type);
                helper.registerUser(user);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Passwords do not match!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_user_type = user_type[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
    }
}