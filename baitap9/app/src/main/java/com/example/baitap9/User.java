package com.example.baitap9;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String fname;
    private String email;
    private String gender;
    private String images;

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getFname() { return fname; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public String getImages() { return images; }
}