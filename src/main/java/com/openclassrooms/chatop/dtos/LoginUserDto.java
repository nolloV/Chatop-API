package com.openclassrooms.chatop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

// DTO (Data Transfer Object) pour les informations de connexion de l'utilisateur
public class LoginUserDto {

    // Adresse email de l'utilisateur
    private String email;

    // Mot de passe de l'utilisateur
    private String password;

    // Date de création de l'utilisateur, mappée à la propriété JSON "created_at"
    @JsonProperty("created_at")
    private Date createdAt;

    // Date de mise à jour de l'utilisateur, mappée à la propriété JSON "updated_at"
    @JsonProperty("updated_at")
    private Date updatedAt;

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

    // Getter pour la date de création
    public Date getCreatedAt() {
        return createdAt;
    }

    // Setter pour la date de création
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getter pour la date de mise à jour
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // Setter pour la date de mise à jour
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}