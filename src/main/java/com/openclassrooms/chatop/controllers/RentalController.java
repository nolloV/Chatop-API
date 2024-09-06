package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.models.Rental;
import com.openclassrooms.chatop.dtos.RentalDto;
import com.openclassrooms.chatop.dtos.RentalResponse;
import com.openclassrooms.chatop.services.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

// Déclare que cette classe est un contrôleur REST et mappe les requêtes à /api/rentals
@RequestMapping("/api/rentals")
@RestController
@SecurityRequirement(name = "bearerAuth")
public class RentalController {

    // Injection du service RentalService
    @Autowired
    private RentalService rentalService;

    // Endpoint pour obtenir toutes les locations
    @GetMapping
    public ResponseEntity<Map<String, List<Rental>>> getAllRentals() {
        // Récupère toutes les locations via le service
        List<Rental> rentals = rentalService.getAllRentals();
        // Retourne les locations dans une réponse HTTP
        return ResponseEntity.ok(Map.of("rentals", rentals));
    }

    // Endpoint pour obtenir une location par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Integer id) {
        // Récupère la location par son ID via le service
        Rental rental = rentalService.getRentalById(id);
        // Si la location n'est pas trouvée, retourne une réponse 404
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        // Retourne la location dans une réponse HTTP
        return ResponseEntity.ok(rental);
    }

    // Endpoint pour créer une nouvelle location
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RentalResponse> createRental(@ModelAttribute RentalDto rentalDto) {
        // Crée une nouvelle location via le service
        Rental newRental = rentalService.createRental(rentalDto);
        // Crée une réponse contenant un message de succès et la nouvelle location
        RentalResponse rentalResponse = new RentalResponse("Rental created!", newRental);
        // Retourne la réponse avec la nouvelle location
        return ResponseEntity.ok(rentalResponse);
    }

    // Endpoint pour mettre à jour une location existante
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RentalResponse> updateRental(
            @PathVariable Integer id,
            @ModelAttribute RentalDto rentalDto) {
        // Met à jour la location via le service
        Rental updatedRental = rentalService.updateRental(id, rentalDto);
        // Crée une réponse contenant un message de succès et la location mise à jour
        RentalResponse rentalResponse = new RentalResponse("Rental updated!", updatedRental);
        // Retourne la réponse avec la location mise à jour
        return ResponseEntity.ok(rentalResponse);
    }
}