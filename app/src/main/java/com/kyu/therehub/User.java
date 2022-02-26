package com.kyu.therehub;

public class User {
    //declaration for the strings
    public String username;
    public String email;
    public String course;
    public String password;

    public User() {//call soon as object created
    }

    public User(String username, String email, String course, String password) {
        this.username = username;
        this.email = email;
        this.course = course;
        this.password = password;
    }
}
