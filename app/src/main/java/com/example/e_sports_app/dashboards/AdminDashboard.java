package com.example.e_sports_app.dashboards;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adminpages.ManageFaq;
import com.example.e_sports_app.adminpages.ManageFeedBack;
import com.example.e_sports_app.adminpages.ManageGames;
import com.example.e_sports_app.adminpages.ManageNotices;
import com.example.e_sports_app.adminpages.ManageUsers;
import com.google.android.material.card.MaterialCardView;

public class AdminDashboard extends Activity {
MaterialCardView open_manage_users,open_manage_notices,open_manage_games,open_manage_faq,open_manage_feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        open_manage_users = findViewById(R.id.open_manage_users);
        open_manage_notices = findViewById(R.id.open_manage_notices);
        open_manage_games = findViewById(R.id.open_manage_games);
        open_manage_faq = findViewById(R.id.open_manage_faq);
        open_manage_feedback = findViewById(R.id.open_manage_feedback);

        open_manage_users.setOnClickListener(v->{
            Intent intent= new Intent(getApplicationContext(), ManageUsers.class);
            startActivity(intent);
        });

        open_manage_notices.setOnClickListener(v->{
            Intent intent= new Intent(getApplicationContext(), ManageNotices.class);
            startActivity(intent);
        });
        open_manage_games.setOnClickListener(v->{
            Intent intent= new Intent(getApplicationContext(), ManageGames.class);
            startActivity(intent);
        });
        open_manage_faq.setOnClickListener(v->{
            Intent intent= new Intent(getApplicationContext(), ManageFaq.class);
            startActivity(intent);
        });
        open_manage_feedback.setOnClickListener(v->{
            Intent intent= new Intent(getApplicationContext(), ManageFeedBack.class);
            startActivity(intent);
        });
    }
}