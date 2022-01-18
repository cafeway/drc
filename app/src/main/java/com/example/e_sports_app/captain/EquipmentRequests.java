package com.example.e_sports_app.captain;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.EquipmentAdapter;
import com.example.e_sports_app.adminpages.ManageTeams;
import com.example.e_sports_app.data.EquipMent;
import com.example.e_sports_app.dialogs.ViewDialog;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class EquipmentRequests extends AppCompatActivity implements EquipmentAdapter.UserListener{

    Toolbar toolbar;
    RecyclerView recyclerView;
    EquipmentAdapter adapter;
    List<EquipMent> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_requests);
        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.my_recycler_view);

        adapter = new EquipmentAdapter(list, this, this);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        setActionBar(toolbar);
        getActionBar().setTitle("Requests");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getRequests();
    }

    private void getRequests() {
        DbHelper helper = new DbHelper(this);
        helper.getRequest(list,adapter);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;

    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getApplicationContext(), "Request Item Clicked", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(EquipmentRequests.this,BookEquipment.class);
                startActivity(intent);
               return true;
        }
        return super.onOptionsItemSelected(item);
    }
}