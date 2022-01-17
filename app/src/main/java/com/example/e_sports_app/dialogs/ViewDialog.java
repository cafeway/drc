package com.example.e_sports_app.dialogs;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_sports_app.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewDialog {
FirebaseFirestore db;
    public void showDialog(Activity activity, String title, String author, String isbn, String category, String url,String id){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);



        SharedPreferences pref = activity.getSharedPreferences("user", MODE_PRIVATE);
        String username = pref.getString("email", "");
db=FirebaseFirestore.getInstance();


        dialog.setCancelable(true);
        dialog.show();
    }
}
