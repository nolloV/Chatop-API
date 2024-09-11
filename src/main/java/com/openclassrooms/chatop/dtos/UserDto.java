package com.openclassrooms.chatop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

// DTO (Data Transfer Object) pour les informations de l'utilisateur
public class UserDto {

    // ID de l'utilisateur, mappé à la propriété JSON "user_id"
    @JsonProperty("id")
    private Integer id;

    // Nom de l'utilisateur, mappé à la propriété JSON "name"
    @JsonProperty("name")
    private String name;

    // Adresse email de l'utilisateur, mappé à la propriété JSON "email"
    @JsonProperty("email")
    private String email;

    // Date de création de l'utilisateur, mappée à la propriété JSON "created_at"
    @JsonProperty("created_at")
    private Date createdAt;

    // Date de mise à jour de l'utilisateur, mappée à la propriété JSON "updated_at"
    @JsonProperty("updated_at")
    private Date updatedAt;

    // Constructeur par défaut
    public UserDto() {
    }

    // Constructeur avec paramètres pour initialiser les propriétés
    public UserDto(Integer id, String name, String email, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter pour l'ID
    public Integer getId() {
        return id;
    }

    // Setter pour l'ID
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter pour le nom
    public String getName() {
        return name;
    }

    // Setter pour le nom
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour l'email
    public String getEmail() {
        return email;
    }

    // Setter pour l'email
    public void setEmail(String email) {
        this.email = email;
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
