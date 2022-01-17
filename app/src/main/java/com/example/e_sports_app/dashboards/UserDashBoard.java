package com.example.e_sports_app.dashboards;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.e_sports_app.R;
import com.example.e_sports_app.userpages.FaqActivity;
import com.example.e_sports_app.userpages.FeedbackActivity;
import com.example.e_sports_app.userpages.GamesActivity;
import com.example.e_sports_app.userpages.NoticeBoard;
import com.google.android.material.card.MaterialCardView;

public class UserDashBoard extends Activity {
MaterialCardView open_games,open_fAs,open_feedback,open_notices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);

        open_fAs =findViewById(R.id.open_manage_faq);
        open_games =findViewById(R.id.open_games_page);
        open_feedback =findViewById(R.id.open_feedback);
        open_notices =findViewById(R.id.open_notice_board);

        open_notices.setOnClickListener(v->startActivity(new Intent(getApplicationContext(), NoticeBoard.class)));
        open_fAs.setOnClickListener(v->startActivity(new Intent(getApplicationContext(), FaqActivity.class)));
        open_feedback.setOnClickListener(v->startActivity(new Intent(getApplicationContext(), FeedbackActivity.class)));
        open_games.setOnClickListener(v->startActivity(new Intent(getApplicationContext(), GamesActivity.class)));

    }
}