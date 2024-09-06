package com.openclassrooms.chatop.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

// DTO (Data Transfer Object) pour les informations de location
public class RentalDto {

    // Nom de la location
    private String name;

    // Description de la location
    private String description;

    // Prix de la location
    private double price;

    // Surface de la location
    private double surface;

    // Image de la location, représentée par un fichier multipart
    private MultipartFile picture; // Changer List<String> en MultipartFile

    // ID du propriétaire de la location
    private Integer ownerId;

    // Date de création de la location
    private LocalDateTime createdAt;

    // Date de mise à jour de la location
    private LocalDateTime updatedAt;

    // Getter pour le nom
    public String getName() {
        return name;
    }

    // Setter pour le nom
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour la description
    public String getDescription() {
        return description;
    }

    // Setter pour la description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter pour le prix
    public double getPrice() {
        return price;
    }

    // Setter pour le prix
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter pour la surface
    public double getSurface() {
        return surface;
    }

    // Setter pour la surface
    public void setSurface(double surface) {
        this.surface = surface;
    }

    // Getter pour l'image
    public MultipartFile getPicture() {
        return picture;
    }

    // Setter pour l'image
    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    // Getter pour l'ID du propriétaire
    public Integer getOwnerId() {
        return ownerId;
    }

    // Setter pour l'ID du propriétaire
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    // Getter pour la date de création
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter pour la date de création
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getter pour la date de mise à jour
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setter pour la date de mise à jour
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
