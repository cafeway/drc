package com.example.e_sports_app.helpers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.e_sports_app.LoginActivity;
import com.example.e_sports_app.dashboards.UserDashBoard;
import com.example.e_sports_app.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DbHelper {
    FirebaseFirestore db;
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

    public  void loginUser(String email,String password)
    {
        db.collection("user").document(email).get().addOnSuccessListener(res->{
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
}
