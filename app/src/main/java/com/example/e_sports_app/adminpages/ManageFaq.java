package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.FaqAdapter;
import com.example.e_sports_app.data.Faq;
import com.example.e_sports_app.data.getFaq;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageFaq extends Activity implements FaqAdapter.UserListener{
Toolbar toolbar;
RecyclerView recyclerView;
FaqAdapter adapter;
List<Faq> list = new ArrayList<>();
DbHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_faq);

        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.my_recycler_view);

        adapter = new FaqAdapter(list,this,this);


        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        setActionBar(toolbar);
        getActionBar().setTitle("Manage FAQ's");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getFAQs();
    }

    private void getFAQs() {
        DbHelper helper = new DbHelper(this);
        helper.getFAQs(list,adapter);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_item_faqs,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_faq_icon:
                Intent intent = new Intent(ManageFaq.this, CreateFaq.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}