package com.openclassrooms.chatop.dtos;

import com.openclassrooms.chatop.models.Rental;

public class RentalResponse {

    private String message;
    private Rental rental;

    public RentalResponse(String message, Rental rental) {
        this.message = message;
        this.rental = rental;
    }

    // Getters et setters
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
}
