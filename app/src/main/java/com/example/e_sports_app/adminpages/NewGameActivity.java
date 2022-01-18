package com.example.e_sports_app.adminpages;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Game;
import com.example.e_sports_app.data.Team;
import com.example.e_sports_app.data.TeamName;
import com.example.e_sports_app.helpers.DbHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NewGameActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner team_one,team_two;
    DatePicker datePicker;
    TimePicker timePicker;
    String[] user_type = { "admin", "Captain", "Facilitator"};

    ArrayList<String> t_one;
    ArrayList<String> t_two;
    String t_one_selected,t_two_selected;
    FirebaseFirestore db;

    Button schedule;

    ArrayList<String> list;
    ArrayList<String> list2;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        team_one = findViewById(R.id.team_one);
        team_two = findViewById(R.id.team_two);
        schedule = findViewById(R.id.btn_schedule_game);
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        db = FirebaseFirestore.getInstance();

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        t_one=new ArrayList<>();
        t_two=new ArrayList<>();
        list.add("select game");
        list2.add("select game");
        team_one.setOnItemSelectedListener(this);
        team_two.setOnItemSelectedListener(this);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaone = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        aaone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        team_one.setAdapter(aaone);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list2);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        team_two.setAdapter(aa);
        db.collection("teams").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                {
                    list.add(documentSnapshot.get("name").toString());
                    list2.add(documentSnapshot.get("name").toString());
                }
            }
        });
        schedule.setOnClickListener(v->
        {
            DbHelper helper = new DbHelper(getApplicationContext());
            int year = datePicker.getYear();
            int month =datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            String finalDate = "" +year +"-" + month+"-" +day;

            String time = timePicker.getHour()+":"+timePicker.getMinute() +"";
            Game game = new Game("",t_one_selected,t_two_selected,finalDate,time,"0","0","upcoming");
             helper.addGame(game);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent!=null) {
            t_one_selected =parent.getSelectedItem().toString();
            t_two_selected =parent.getSelectedItem().toString();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}