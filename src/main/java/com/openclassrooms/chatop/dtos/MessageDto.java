package com.openclassrooms.chatop.dtos;

import java.time.LocalDateTime;

public class MessageDto {

    // Champs représentant les données du message
    private String message; // Contenu du message
    private String sender; // Nom de l'expéditeur
    private Integer rental_id; // Identifiant de la location associée
    private LocalDateTime createdAt; // Date et heure de création
    private LocalDateTime updatedAt; // Date et heure de la dernière mise à jour

    // Getters et Setters pour accéder et modifier les champs privés
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getRental_id() {
        return rental_id;
    }

    public void setRental_id(Integer rental_id) {
        this.rental_id = rental_id;
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
}
