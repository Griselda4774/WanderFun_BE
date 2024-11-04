package com.project2.wanderfun.domain.model;

public abstract class BaseUser {
    protected String role;
    protected String email;
    protected String password;
    protected String firstName;
    protected String lastName;

    public BaseUser() {
    }

    public BaseUser(String role, String password, String email, String lastName, String firstName) {
        this.role = role;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
