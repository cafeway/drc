package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.NoticeAdapter;
import com.example.e_sports_app.adapters.TeamAdapter;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.data.Team;
import com.example.e_sports_app.dialogs.ViewDialog;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageTeams extends Activity implements TeamAdapter.UserListener{
    Toolbar toolbar;
    RecyclerView recyclerView;
    TeamAdapter adapter;
    List<Team> list = new ArrayList<>();

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_teams);
        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.my_recycler_view);


        adapter = new TeamAdapter(list,this,this);

        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        setActionBar(toolbar);
        getActionBar().setTitle("Manage Teams");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getTeams();

    }
    private void getTeams() {
        DbHelper helper = new DbHelper(this);
        helper.getTeams(list,adapter);
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
    public void onAddBtnClick(int position) {
        Team item = list.get(position);
        Intent intent = new Intent(getApplicationContext(),AddPlayerActivity.class);
        intent.putExtra("team_id",item.getId());
        startActivity(intent);

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
                ViewDialog dialog = new ViewDialog();
                dialog.showAddTeamDialog(ManageTeams.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}