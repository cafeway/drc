package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.FeedbackAdapter;
import com.example.e_sports_app.adapters.UserAdapter;
import com.example.e_sports_app.data.Feedback;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageFeedBack extends Activity implements FeedbackAdapter.UserListener{

    FeedbackAdapter adapter;
    List<Feedback> list = new ArrayList<>();
    RecyclerView recyclerView;
    DbHelper helper;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_feed_back);

        adapter = new FeedbackAdapter(list,this,this);
        recyclerView = findViewById(R.id.my_recycler_view);
        toolbar = findViewById(R.id.toolBar);
        helper = new DbHelper(this);
        adapter = new FeedbackAdapter(list,getApplicationContext(),this);

        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        setActionBar(toolbar);
        getActionBar().setTitle("Manage FeedBack");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getFeedBack();
    }

    private void getFeedBack() {
        DbHelper helper = new DbHelper(this);
        helper.getFeedback(list,adapter);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, "Feedback item clicked!", Toast.LENGTH_SHORT).show();
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