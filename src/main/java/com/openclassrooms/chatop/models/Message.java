package com.openclassrooms.chatop.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

// Indique que cette classe est une entité JPA mappée à une table de la base de données
@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Identifiant unique du message

    @Column(name = "message", length = 2000)
    private String content; // Contenu du message

    @Column(name = "sender")
    private String sender; // Nom de l'expéditeur du message

    @Column(name = "user_id")
    private Integer userId; // Identifiant de l'utilisateur qui a envoyé le message

    @Column(name = "rental_id")
    private Integer rentalId; // Identifiant de la location associée au message

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Date et heure de création du message

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Date et heure de la dernière mise à jour du message

    // Getters et Setters pour accéder et modifier les champs privés
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
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
