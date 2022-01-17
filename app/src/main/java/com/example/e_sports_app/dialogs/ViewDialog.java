package com.example.e_sports_app.dialogs;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Team;
import com.example.e_sports_app.helpers.DbHelper;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewDialog {
        FirebaseFirestore db;
    public void showAddTeamDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_team);
        db=FirebaseFirestore.getInstance();

        DbHelper helper = new DbHelper(activity);
        EditText title,description;
        title = dialog.findViewById(R.id.team_name);
        description = dialog.findViewById(R.id.team_description);
        Button add_team;
        add_team =dialog.findViewById(R.id.team_add_btn);
        add_team.setOnClickListener(v->{

            Team team = new Team("",title.getText().toString(),description.getText().toString());
            helper.addTeam(team);
            dialog.dismiss();
        });

        dialog.setCancelable(true);
        dialog.show();
    }
}
