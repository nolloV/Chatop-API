package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.models.Rental;
import com.openclassrooms.chatop.dtos.RentalDto;
import com.openclassrooms.chatop.dtos.RentalResponse;
import com.openclassrooms.chatop.services.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;

@RequestMapping("/api/rentals")
@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<Map<String, List<Rental>>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(Map.of("rentals", rentals));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RentalResponse> createRental(
            @ModelAttribute RentalDto rentalDto) {
        // Utiliser le service pour créer une nouvelle location
        Rental newRental = rentalService.createRental(rentalDto);
    
        // Créer la réponse avec le message
        RentalResponse rentalResponse = new RentalResponse("Rental created !", newRental);
    
        return ResponseEntity.ok(rentalResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody RentalDto rentalDto) {
        Rental updatedRental = rentalService.updateRental(id, rentalDto);
        return ResponseEntity.ok(updatedRental);
    }
}
