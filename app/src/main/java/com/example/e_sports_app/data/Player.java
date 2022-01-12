package com.example.e_sports_app.data;

public class Player {
    String name,shirt_no,role;

    public Player(String name, String shirt_no, String role) {
        this.name = name;
        this.shirt_no = shirt_no;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShirt_no() {
        return shirt_no;
    }

    public void setShirt_no(String shirt_no) {
        this.shirt_no = shirt_no;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
