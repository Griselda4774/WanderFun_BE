package com.project2.wanderfun.domain.model;

public class Admin extends BaseUser{
    public Admin() {
    }

    public Admin(String role, String password, String email, String lastName, String firstName) {
        super(role, password, email, lastName, firstName);
    }

    @Override
    public String getRole() {
        return super.getRole();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setRole(String role) {
        super.setRole(role);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }
}
