package com.example.weather;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private String name;
    private String lastName;
    private String pass;

    public User(String email, String name, String lastName, String pass) { // builder operation
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.pass = pass;
    }
    public User (){}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {return name;}

    public String getPass(){return pass;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
