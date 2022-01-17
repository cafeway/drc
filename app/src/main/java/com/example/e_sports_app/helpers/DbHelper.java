package com.example.e_sports_app.helpers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.LoginActivity;
import com.example.e_sports_app.adapters.FaqAdapter;
import com.example.e_sports_app.adapters.FeedbackAdapter;
import com.example.e_sports_app.adapters.GamesAdapter;
import com.example.e_sports_app.adapters.NoticeAdapter;
import com.example.e_sports_app.adapters.TeamAdapter;
import com.example.e_sports_app.adapters.UserAdapter;
import com.example.e_sports_app.adapters.UserGamesAdapter;
import com.example.e_sports_app.adapters.UserNoticeBoard;
import com.example.e_sports_app.dashboards.AdminDashboard;
import com.example.e_sports_app.dashboards.UserDashBoard;
import com.example.e_sports_app.data.Faq;
import com.example.e_sports_app.data.Feedback;
import com.example.e_sports_app.data.Game;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.data.Player;
import com.example.e_sports_app.data.Team;
import com.example.e_sports_app.data.User;
import com.example.e_sports_app.data.getFaq;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;

    public DbHelper(Context context) {
        this.context = context;
    }

    public void checkUser(User user){
        db.collection("users").document(user.getEmail()).get().addOnSuccessListener(task->{
            if (!task.exists())
            {
                createAdmin(user);
            }
        });
    }
    public void createAdmin(User user)
    {
        db.collection("users").document(user.getEmail()).set(user).addOnCompleteListener(task -> {
            Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, error.getMessage()+ "Failed to register user!", Toast.LENGTH_SHORT).show();
        });
    }
    public void registerUser(User user)
    {
        db.collection("users").document(user.getEmail()).set(user).addOnCompleteListener(task -> {
            Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, error.getMessage()+ "Failed to register user!", Toast.LENGTH_SHORT).show();
        });

    }
    public void adminAddUser(User user)
    {
        db.collection("users").document(user.getEmail()).set(user).addOnCompleteListener(task -> {
            Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, error.getMessage()+ "Failed to register user!", Toast.LENGTH_SHORT).show();
        });

    }

    public  void loginUser(String email,String password)
    {
        db.collection("users").document(email).get().addOnSuccessListener(res->{
            if(res.exists())
            {
                String pass= res.get("password").toString();
                if (pass.equals(password))
                {
                    Toast.makeText(context, "Logged in successfully!!", Toast.LENGTH_SHORT).show();
                    if (res.get("usertype").toString().equals("user")) {
                        context.startActivity(new Intent(context, UserDashBoard.class));
                    }
                    else if (res.get("usertype").toString().equals("admin"))
                    {
                        context.startActivity(new Intent(context, AdminDashboard.class));
                    }
                }
            }
            else {
                Toast.makeText(context, "User Does Not Exist!", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    Manage User Admin functions

    public void deleteUser(String email)
    {
        db.collection("users").document(email).delete().addOnSuccessListener(task->{
            Toast.makeText(context, "User Deleted Successfully!", Toast.LENGTH_SHORT).show();
        });
    }
    public void approveUser(String email)
    {
        db.collection("users").document(email).update("status","approved").addOnSuccessListener(task->{
            Toast.makeText(context, "User Approved Successfully!", Toast.LENGTH_SHORT).show();
        });
    }
    public void disableUser(String email)
    { 
        db.collection("users").document(email).update("status","disabled").addOnSuccessListener(task->{
            Toast.makeText(context, "User Disabled Successfully!", Toast.LENGTH_SHORT).show();
        });  
    }
    public void rejectUser(String email)
    {
        db.collection("users").document(email).update("status","rejected").addOnSuccessListener(task->{
            Toast.makeText(context, "User Rejected Successfully!", Toast.LENGTH_SHORT).show();
        });
    }
//Adding data functions


    public void submitFeedBack(Feedback feedback)
    {
        db.collection("feedback").document().set(feedback).addOnSuccessListener(task-> {
            Toast.makeText(context, "Feedback sent successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, "Failed to send feedback error=>:"+error.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void addGame(Game game)
    {
        db.collection("games").document().set(game).addOnSuccessListener(task-> {
            Toast.makeText(context, "Game added successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, "Failed to add game error=>:"+error.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void addPlayer(Player player,String game_id)
    {
        db.collection("games").document(game_id).collection("players").document().set(player).addOnSuccessListener(task-> {
            Toast.makeText(context, "Player added successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, "Failed to add game error=>:"+error.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void sendNotice(Notice notice)
    {
        db.collection("notices").document().set(notice).addOnSuccessListener(task-> {
            Toast.makeText(context, "Notice sent successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, "Failed to send notice, error=>:"+error.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void sendFaqs(Faq faq)
    {
        db.collection("faq").document().set(faq).addOnSuccessListener(task-> {
            Toast.makeText(context, "faq sent successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, "Failed to add  faq's error=>:"+error.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


//    Getting data functions

    public void getNotices(List<Notice> list, NoticeAdapter adapter)
    {
        db.collection("notices").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                    Notice notice = new Notice(documentSnapshot.get("title").toString(),documentSnapshot.get("description").toString(),null);

                   list.add(notice);
                   adapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }

    public void getUsers(List<User> list, UserAdapter adapter)
    {
        db.collection("users").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult())
                {
                    if (doc.exists())
                    {
                        User user = new User(doc.getString("name"),doc.getString("email"),doc.getString("phone_number"),"",doc.getString("status"),doc.getString("usertype"));
                        list.add(user);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    public void getFeedback(List<Feedback> list, FeedbackAdapter adapter)
    {
        db.collection("feedback").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult())
                {
                    if (doc.exists())
                    {
                        Feedback feedback = new Feedback(doc.getString("title"),doc.getString("description"));
                        list.add(feedback);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    public void getGames(List<Game> list, GamesAdapter adapter) {
        db.collection("games").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult())
                {
                    if (doc.exists())
                    {
                        Game game = new Game(doc.getString("team1_name"), doc.getString("team2_name"), doc.getString("play_date"),"start_time","end_time",doc.getString("score_team1"),doc.getString("score_team_2"),"game_status");
                        list.add(game);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    public void getFAQs(List<Faq> list, FaqAdapter adapter) {
        db.collection("faq").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult())
                {
                    if (doc.exists())
                    {
                        Faq faq = new Faq(doc.getString("title"),doc.getString("description"));
                        list.add(faq);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    public void getUserNotices(List<Notice> list, UserNoticeBoard adapter) {
        db.collection("notices").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                        Notice notice = new Notice(documentSnapshot.get("title").toString(),documentSnapshot.get("description").toString(),null);

                        list.add(notice);
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }

    public void getUserGames(List<Game> list, UserGamesAdapter adapter) {
        db.collection("games").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult())
                {
                    if (doc.exists())
                    {
                        Game game = new Game(doc.getString("team1_name"), doc.getString("team2_name"), doc.getString("play_date"),"start_time","end_time",doc.getString("score_team1"),doc.getString("score_team_2"),"game_status");
                        list.add(game);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }
    public void addTeam(Team team)
    {
        db.collection("teams").document().set(team).addOnSuccessListener(task->{
            Toast.makeText(context, "Team Added successfully.", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, "Failed to add team!", Toast.LENGTH_SHORT).show();
        });
    }
    public void addPlayer(Player player)
    {
        db.collection("players").document().set(player).addOnSuccessListener(task->{
            Toast.makeText(context, "Player Added successfully.", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(error->{
            Toast.makeText(context, "Failed to add player!", Toast.LENGTH_SHORT).show();
        });
    }


    public void getTeams(List<Team> list, TeamAdapter adapter) {
        db.collection("teams").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                        Team team = new Team(documentSnapshot.getId(),documentSnapshot.get("name").toString(),documentSnapshot.get("description").toString());

                        list.add(team);
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }



}
