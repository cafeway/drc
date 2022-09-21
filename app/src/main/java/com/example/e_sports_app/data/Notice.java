package com.example.e_sports_app.data;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Notice {
    String title,description;
    @ServerTimestamp Date time;

    public Notice(String title, String description, Date time) {
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
