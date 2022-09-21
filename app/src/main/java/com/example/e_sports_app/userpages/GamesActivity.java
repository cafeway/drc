package com.example.e_sports_app.userpages;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.adapters.UserGamesAdapter;
import com.example.e_sports_app.adapters.UserNoticeBoard;
import com.example.e_sports_app.data.Game;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.helpers.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class GamesActivity extends Activity {
    UserGamesAdapter adapter;
    List<Game> list = new ArrayList<>();
    RecyclerView recyclerView;
    DbHelper helper;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        recyclerView = findViewById(R.id.my_recycler_view);
        toolbar = findViewById(R.id.toolBar);
        helper = new DbHelper(this);
        adapter = new UserGamesAdapter(list,getApplicationContext());

        LinearLayoutManager manager =new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        setActionBar(toolbar);
        getActionBar().setTitle("Games");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        getGames();
    }

    private void getGames() {
        DbHelper helper = new DbHelper(this);
        helper.getUserGames(list,adapter);
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