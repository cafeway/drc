package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Player;
import com.example.e_sports_app.data.Team;
import com.example.e_sports_app.helpers.DbHelper;

public class AddPlayerActivity extends Activity implements AdapterView.OnItemSelectedListener {
EditText player_name,player_shirt_no;
Spinner spinner;
String selected_user_type;
String team_id;
Button team_add_btn;
Toolbar toolbar;
    String[] user_type = { "player", "captain", "goalkeeper"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        player_name = findViewById(R.id.player_name);
        player_shirt_no = findViewById(R.id.player_shirt_number);
        spinner = findViewById(R.id.player_role);
        team_add_btn = findViewById(R.id.team_add_btn);
        toolbar = findViewById(R.id.toolBar);
        setActionBar(toolbar);
        getActionBar().setTitle("Add Players");
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        team_id = getIntent().getStringExtra("team_id");

//        Initializing listener for spinner

        spinner.setOnItemSelectedListener(this);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,user_type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        //        Initialize dbHelper

        DbHelper helper = new DbHelper(getApplicationContext());

        team_add_btn.setOnClickListener(v->{
            Player player = new Player(player_name.getText().toString(),player_shirt_no.getText().toString(),selected_user_type,team_id);
            helper.addPlayer(player,team_id);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_user_type = user_type[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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