package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Faq;
import com.example.e_sports_app.helpers.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class CreateFaq extends Activity {
TextInputEditText faq_title;
EditText faq_message;
Button send_faq;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_faq);

        faq_title = findViewById(R.id.faq_title);
        faq_message = findViewById(R.id.faq_message);
        send_faq = findViewById(R.id.send_faq);
        toolbar = findViewById(R.id.toolBar);
        setActionBar(toolbar);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        send_faq.setOnClickListener(v->{
            DbHelper helper = new DbHelper(this);
            Faq faq = new Faq(faq_title.getText().toString(),faq_message.getText().toString());
            helper.sendFaqs(faq);
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

}