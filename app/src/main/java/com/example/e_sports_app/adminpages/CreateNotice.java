package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.helpers.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class CreateNotice extends Activity {
EditText notification_message;
TextInputEditText notification_title;
Button send_notification;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);

        notification_message = findViewById(R.id.notification_message);
        notification_title = findViewById(R.id.notification_title);
        send_notification = findViewById(R.id.send_notification);
        toolbar = findViewById(R.id.toolBar);

        setActionBar(toolbar);
        getActionBar().setTitle("Send Notification");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        send_notification.setOnClickListener(v->{
            DbHelper helper = new DbHelper(this);
            Notice notice = new Notice(notification_title.getText().toString(),notification_message.getText().toString(),null);
            helper.sendNotice(notice);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return  true;
    }
}