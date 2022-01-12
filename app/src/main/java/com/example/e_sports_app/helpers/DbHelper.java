package com.example.e_sports_app.helpers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.LoginActivity;
import com.example.e_sports_app.dashboards.UserDashBoard;
import com.example.e_sports_app.data.Faq;
import com.example.e_sports_app.data.Feedback;
import com.example.e_sports_app.data.Game;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.data.Player;
import com.example.e_sports_app.data.User;
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
                    Toast.makeText(context, "User logged in successfully!!", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, UserDashBoard.class));
                }
            }
            else {
                Toast.makeText(context, "User Does Not Exist!", Toast.LENGTH_SHORT).show();
            }
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

    public void getNotices(RecyclerView recyclerView)
    {
        db.collection("notices").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {

                    }

                }
            }
        });
    }



}
