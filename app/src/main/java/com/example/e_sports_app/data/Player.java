package com.example.e_sports_app.data;

public class Player {
    String name,shirt_no,role,team_id;

    public Player(String name, String shirt_no, String role, String team_id) {
        this.name = name;
        this.shirt_no = shirt_no;
        this.role = role;
        this.team_id = team_id;
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

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }
}
