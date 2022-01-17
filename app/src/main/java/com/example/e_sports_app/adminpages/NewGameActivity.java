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
import com.example.e_sports_app.data.Team;
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
    String[] user_type = { "Admin", "Captain", "Facilitator"};

    ArrayList<String> t_one;
    ArrayList<String> t_two;
    String t_one_selected,t_two_selected;
    FirebaseFirestore db;

    Button schedule;
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

        t_one=new ArrayList<>();
        t_two=new ArrayList<>();

        team_one.setOnItemSelectedListener(this);
        team_two.setOnItemSelectedListener(this);

        db.collection("teams").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                {
                    t_one.add(documentSnapshot.get("name").toString());
                    t_two.add(documentSnapshot.get("name").toString());
                }
            }
        });

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaone = new ArrayAdapter(this,android.R.layout.simple_spinner_item,user_type);
        aaone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        team_one.setAdapter(aaone);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,user_type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        team_two.setAdapter(aa);

        schedule.setOnClickListener(v->
        {
            DbHelper helper = new DbHelper(getApplicationContext());
            int year = datePicker.getYear();
            int month =datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            String finalDate = "" +year +"-" + month+"-" +day;

            String time = timePicker.getHour()+":"+timePicker.getMinute() +"";


        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()){
            case R.id.team_one:
                t_one_selected = t_one.get(position);
                break;
            case R.id.team_two:
                t_two_selected = t_two.get(position);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}