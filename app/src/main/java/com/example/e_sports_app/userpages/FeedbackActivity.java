package com.example.e_sports_app.userpages;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Feedback;
import com.example.e_sports_app.helpers.DbHelper;

public class FeedbackActivity extends Activity {
EditText feedback_title,feedback_description;

Button send_feedback;

DbHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback_title = findViewById(R.id.feedback_title);
        feedback_description = findViewById(R.id.feedback_description);

        send_feedback = findViewById(R.id.send_feedback);

        helper = new DbHelper(this);

        send_feedback.setOnClickListener(v -> {
            Feedback feedback = new Feedback(feedback_title.getText().toString(),feedback_description.getText().toString());
            helper.submitFeedBack(feedback);
        });
    }
}