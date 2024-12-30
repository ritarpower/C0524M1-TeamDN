package com.example.casem4.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AppUser> users;

    public AppRole() {
    }

    public AppRole(int id, String role, List<AppUser> users) {
        this.id = id;
        this.role = role;
        this.users = users;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }
}
