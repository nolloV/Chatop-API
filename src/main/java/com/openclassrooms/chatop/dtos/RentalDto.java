package com.openclassrooms.chatop.dtos;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.chatop.entities.Rental;

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

    // Champs de RentalResponse
    private String message;
    private Rental rental;

    // Getters et setters pour les champs existants
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Getters et setters pour les champs de RentalResponse
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    // Constructeur pour initialiser le message et la location
    public RentalDto(String message, Rental rental) {
        this.message = message;
        this.rental = rental;
    }

    // Constructeur par défaut
    public RentalDto() {
    }
}
