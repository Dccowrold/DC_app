package com.assoftek.splashscreen;

public class LoginRequest {
    private String id;
    private String email;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
