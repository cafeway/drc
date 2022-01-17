package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.NoticeAdapter;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.data.User;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageNotices extends Activity {
    Toolbar toolbar;
RecyclerView recyclerView;
NoticeAdapter adapter;
    List<Notice> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_notices);

        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.my_recycler_view);


        adapter = new NoticeAdapter(list,this);

        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        setActionBar(toolbar);
        getActionBar().setTitle("Manage Notifications");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getNotices();

    }
    private void getNotices() {
        DbHelper helper = new DbHelper(this);
        helper.getNotices(list,adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_notices,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_icon:
                Intent intent = new Intent(ManageNotices.this, CreateNotice.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}