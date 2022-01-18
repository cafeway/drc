package com.example.e_sports_app.captain;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.EquipMent;
import com.example.e_sports_app.helpers.DbHelper;

public class BookEquipment extends AppCompatActivity {
Button submit_request;
EditText request_title,request_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_equipment);
        submit_request = findViewById(R.id.submit_request);
        request_title = findViewById(R.id.request_title);
        request_message = findViewById(R.id.request_message);

        submit_request.setOnClickListener(v->{
            DbHelper helper = new DbHelper(this);
            EquipMent equipMent = new EquipMent("NA",request_title.getText().toString(),request_message.getText().toString(),"pending");
            helper.sendRequest(equipMent);
        });
    }
}