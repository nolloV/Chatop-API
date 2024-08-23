package com.openclassrooms.chatop.dtos;

public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;

    // Getter pour email
    public String getEmail() {
        return email;
    }

    // Setter pour email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter pour password
    public String getPassword() {
        return password;
    }

    // Setter pour password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter pour fullName
    public String getFullName() {
        return fullName;
    }

    // Setter pour fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}