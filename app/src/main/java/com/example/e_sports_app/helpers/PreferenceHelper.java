package com.example.e_sports_app.helpers;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public PreferenceHelper(Context context) {
        this.context = context;
    }
    public void setLimit(int limit)
    {
        pref=context.getSharedPreferences("user",MODE_PRIVATE);
        editor=pref.edit();
        editor.putInt("limit",limit);
        editor.commit();
    }
    public int getLimit()
    {
        pref=context.getSharedPreferences("user",context.MODE_PRIVATE);
        int n=pref.getInt("limit",0);
        return n;
    }
    public String getEmail() {
        pref=context.getSharedPreferences("user",context.MODE_PRIVATE);
        String email=pref.getString("email","");
        return email;
    }

    public void setEmail(String email)
    {
        pref=context.getSharedPreferences("user",MODE_PRIVATE);
        editor=pref.edit();
        editor.putString("email",email);
        editor.commit();
    }

    public String getType() {
        pref=context.getSharedPreferences("user",context.MODE_PRIVATE);
        String type=pref.getString("type","");
        return type;
    }

    public void setType(String type)
    {
        pref=context.getSharedPreferences("user",MODE_PRIVATE);
        editor=pref.edit();
        editor.putString("type",type);
        editor.commit();
    }

    public String getStatus() {
        pref=context.getSharedPreferences("user",context.MODE_PRIVATE);
        String status=pref.getString("status","");
        return status;
    }

    public void setStatus(String status)
    {
        pref=context.getSharedPreferences("user",MODE_PRIVATE);
        editor=pref.edit();
        editor.putString("status",status);
        editor.commit();
    }
}
