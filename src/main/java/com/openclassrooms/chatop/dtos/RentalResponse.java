package com.openclassrooms.chatop.dtos;

import com.openclassrooms.chatop.models.Rental;

// DTO (Data Transfer Object) pour la réponse contenant les informations de location
public class RentalResponse {

    // Message de la réponse
    private String message;

    // Objet Rental contenant les détails de la location
    private Rental rental;

    // Constructeur pour initialiser le message et la location
    public RentalResponse(String message, Rental rental) {
        this.message = message;
        this.rental = rental;
    }

    // Getter pour le message
    public String getMessage() {
        return message;
    }

    // Setter pour le message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter pour la location
    public Rental getRental() {
        return rental;
    }

    // Setter pour la location
    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
