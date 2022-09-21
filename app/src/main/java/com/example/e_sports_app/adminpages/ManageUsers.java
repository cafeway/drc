package com.example.e_sports_app.adminpages;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.UserAdapter;
import com.example.e_sports_app.data.User;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageUsers extends Activity implements UserAdapter.UserListener {
    UserAdapter adapter;
    List<User> list = new ArrayList<>();
    RecyclerView recyclerView;
    DbHelper helper;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);
        recyclerView = findViewById(R.id.my_recycler_view);
        toolbar = findViewById(R.id.toolBar);
        helper = new DbHelper(this);
        adapter = new UserAdapter(list,getApplicationContext(),this);

        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        setActionBar(toolbar);
        getActionBar().setTitle("Manage Users");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getUsers();
    }

    private void getUsers() {
        DbHelper helper = new DbHelper(this);
        helper.getUsers(list,adapter);
    }

    @Override
    public void onApproveUserClick(int position) {
        User userItem = list.get(position);
        confirm("approve",userItem);
    }

    @Override
    public void onRejectUserClick(int position) {
        User userItem = list.get(position);
        confirm("reject",userItem);
    }

    @Override
    public void onDeleteUserClick(int position) {
        User userItem = list.get(position);
        confirm("delete",userItem);
    }

    @Override
    public void onDisableUserClick(int position) {
        User userItem = list.get(position);
        confirm("disable",userItem);
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
        getMenuInflater().inflate(R.menu.menu_item_users,menu);
        return true;
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_user_icon:
                Intent intent = new Intent(ManageUsers.this, CreateUser.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirm(String message, User userItem)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(ManageUsers.this);
        alert.setTitle(message);
        alert.setMessage("Confirm "+message);
        alert.setPositiveButton("Yes", (dialog, which) -> {
            if (message.equals("delete"))
            {
                helper.deleteUser(userItem.getEmail());
                dialog.dismiss();
            }
            else if(message.equals("approve"))
            {
                helper.approveUser(userItem.getEmail());
                dialog.dismiss();
            }
            else if (message.equals("reject"))
            {
                helper.rejectUser(userItem.getEmail());
                dialog.dismiss();
            }
            else if (message.equals("disable"))
            {
                helper.disableUser(userItem.getEmail());
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        alert.show();
    }
}