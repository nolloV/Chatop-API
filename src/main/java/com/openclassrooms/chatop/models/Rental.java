package com.openclassrooms.chatop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

// Déclare que cette classe est une entité JPA
@Entity
public class Rental {

    // Identifiant unique de la location, généré automatiquement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nom de la location
    private String name;

    // Description de la location
    private String description;

    // Prix de la location
    private double price;

    // Surface de la location
    private double surface;

    // Chemin ou URL de l'image de la location
    private String picture;

    // Identifiant du propriétaire de la location
    private Integer ownerId;

    // Date de création de la location, non modifiable après insertion
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    // Date de mise à jour de la location
    @Column(name = "updated_at", nullable = false)
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    // Méthode appelée avant l'insertion d'une nouvelle entité pour définir les dates de création et de mise à jour
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Méthode appelée avant la mise à jour d'une entité existante pour définir la date de mise à jour
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getter pour l'identifiant de la location
    public Integer getId() {
        return id;
    }

    // Setter pour l'identifiant de la location
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter pour le nom de la location
    public String getName() {
        return name;
    }

    // Setter pour le nom de la location
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour la description de la location
    public String getDescription() {
        return description;
    }

    // Setter pour la description de la location
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter pour le prix de la location
    public double getPrice() {
        return price;
    }

    // Setter pour le prix de la location
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter pour la surface de la location
    public double getSurface() {
        return surface;
    }

    // Setter pour la surface de la location
    public void setSurface(double surface) {
        this.surface = surface;
    }

    // Getter pour l'image de la location
    public String getPicture() {
        return picture;
    }

    // Setter pour l'image de la location
    public void setPicture(String picture) {
        this.picture = picture;
    }

    // Getter pour l'identifiant du propriétaire de la location
    public Integer getOwnerId() {
        return ownerId;
    }

    // Setter pour l'identifiant du propriétaire de la location
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    // Getter pour la date de création de la location
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter pour la date de création de la location
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getter pour la date de mise à jour de la location
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setter pour la date de mise à jour de la location
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
