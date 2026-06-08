package model;

import enums.Role;

public class User {
    private String id;
    private String userName;
    private String password;
    private Role role;
    public User(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    public String getUsername() {
        return userName;
    }
}
