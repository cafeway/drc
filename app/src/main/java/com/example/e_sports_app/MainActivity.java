package com.example.e_sports_app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_sports_app.helpers.PreferenceHelper;

public class MainActivity extends Activity {
    Button open_register,open_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        open_login = findViewById(R.id.open_login);
        open_register = findViewById(R.id.open_register);
       open_login.setOnClickListener(v->{
            startActivity(new Intent(this,LoginActivity.class));
        });


        open_register.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class));
        });

    }

}