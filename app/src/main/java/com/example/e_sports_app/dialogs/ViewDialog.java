package com.example.e_sports_app.dialogs;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Player;
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
    public void showFaq(Activity activity,String id){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list_faq);
        db=FirebaseFirestore.getInstance();
        TextView title,description;
        title = dialog.findViewById(R.id.faq_question);
        description = dialog.findViewById(R.id.faq_description);

        db.collection("faq").document(id).get().addOnSuccessListener(result->{
           if (result.exists())
           {
               title.setText(result.getString("title"));
               description.setText(result.getString("description"));
           }
        });
     dialog.setCancelable(true);
        dialog.show();
    }

    public void showResultsDialog(Activity activity,String game_id){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game_results);
        db=FirebaseFirestore.getInstance();
        TextView team_one,team_two;
        team_one = dialog.findViewById(R.id.tv_team_one);
        team_two = dialog.findViewById(R.id.tv_team_two);
        EditText result_one = dialog.findViewById(R.id.result_team_1);
        EditText result_two = dialog.findViewById(R.id.result_team_2);

        Button add_results = dialog.findViewById(R.id.add_results_btn);
        db.collection("games").document(game_id).get().addOnSuccessListener(result->{
            if (result.exists())
            {
                team_one.setText(result.getString("team1_name"));
                team_two.setText(result.getString("team2_name"));
                result_one.setText(result.getString("score_team1"));
                result_two.setText(result.getString("score_team_2"));
            }
        });

        add_results.setOnClickListener(vv->
                {
                    DbHelper helper = new DbHelper(activity);
                    helper.updateResults(result_one.getText().toString(),result_two.getText().toString(),game_id);
                }
                );
        dialog.setCancelable(true);
        dialog.show();
    }
}
