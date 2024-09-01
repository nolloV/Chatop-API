package com.openclassrooms.chatop.dtos;

// DTO (Data Transfer Object) pour l'enregistrement des utilisateurs
public class RegisterUserDto {

    // Adresse email de l'utilisateur
    private String email;

    // Mot de passe de l'utilisateur
    private String password;

    // Nom de l'utilisateur
    private String name;

    // Getter pour l'email
    public String getEmail() {
        return email;
    }

    // Setter pour l'email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter pour le mot de passe
    public String getPassword() {
        return password;
    }

    // Setter pour le mot de passe
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter pour le nom
    public String getName() {
        return name;
    }

    // Setter pour le nom
    public void setName(String name) {
        this.name = name;
    }
}
