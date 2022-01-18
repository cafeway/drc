package com.example.e_sports_app.data;

public class Game {
    String game_id, team1_name, team2_name, play_date, play_time, score_team1, score_team_2, game_status;

    public Game(String game_id, String team1_name, String team2_name, String play_date, String play_time, String score_team1, String score_team_2, String game_status) {
        this.game_id = game_id;
        this.team1_name = team1_name;
        this.team2_name = team2_name;
        this.play_date = play_date;
        this.play_time = play_time;
        this.score_team1 = score_team1;
        this.score_team_2 = score_team_2;
        this.game_status = game_status;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getTeam1_name() {
        return team1_name;
    }

    public void setTeam1_name(String team1_name) {
        this.team1_name = team1_name;
    }

    public String getTeam2_name() {
        return team2_name;
    }

    public void setTeam2_name(String team2_name) {
        this.team2_name = team2_name;
    }

    public String getPlay_date() {
        return play_date;
    }

    public void setPlay_date(String play_date) {
        this.play_date = play_date;
    }

    public String getPlay_time() {
        return play_time;
    }

    public void setPlay_time(String play_time) {
        this.play_time = play_time;
    }

    public String getScore_team1() {
        return score_team1;
    }

    public void setScore_team1(String score_team1) {
        this.score_team1 = score_team1;
    }

    public String getScore_team_2() {
        return score_team_2;
    }

    public void setScore_team_2(String score_team_2) {
        this.score_team_2 = score_team_2;
    }

    public String getGame_status() {
        return game_status;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }
}
