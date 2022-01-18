package com.example.e_sports_app;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.e_sports_app.dashboards.UserDashBoard;
import com.example.e_sports_app.data.User;
import com.example.e_sports_app.helpers.DbHelper;
import com.example.e_sports_app.helpers.PreferenceHelper;

public class SplashActivity extends Activity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    DbHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        helper =new DbHelper(this);

        User user = new User("Admin","admin@gmail.com","072200000","admin1234","approved","admin");
        helper.checkUser(user);
        new Handler().postDelayed(() -> checkLogin(),2000);

    }

    private void checkLogin() {

        if (pref==null)
        {
            pref=getSharedPreferences("user",MODE_PRIVATE);

            String username=pref.getString("email","");
            if (username!=null && !username.equals(""))
            {
                Intent intent=new Intent(getApplicationContext(), UserDashBoard.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        }
}}