package com.example.e_sports_app.data;

public class User {
    String name;
    String email;
    String phone_number;
    String password;
    String status;
    String usertype;
    public User(String name, String email, String phone_number, String password, String status,String usertype) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.status = status;
        this.usertype = usertype;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
