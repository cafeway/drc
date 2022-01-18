package com.example.e_sports_app.adminpages;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.AdminEquipmentAdapter;
import com.example.e_sports_app.adapters.EquipmentAdapter;
import com.example.e_sports_app.data.EquipMent;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageRequests extends Activity implements AdminEquipmentAdapter.UserListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    AdminEquipmentAdapter adapter;
    List<EquipMent> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_requests);
        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.my_recycler_view);

        adapter = new AdminEquipmentAdapter(list, this, this);


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
        helper.getAdminRequest(list,adapter);
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
    public void onRejectButtonClicked(int position) {
        EquipMent item = list.get(position);
        DbHelper helper = new DbHelper(getApplicationContext());
        helper.rejectRequest(item.getId());
    }

    @Override
    public void onApproveButtonClicked(int position) {
        EquipMent item = list.get(position);
        DbHelper helper = new DbHelper(getApplicationContext());
        helper.approveRequest(item.getId());
    }


}