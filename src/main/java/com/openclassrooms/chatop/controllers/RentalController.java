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

@RequestMapping("/api/rentals")
@RestController
@SecurityRequirement(name = "bearerAuth")
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
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RentalResponse> createRental(@ModelAttribute RentalDto rentalDto) {
        Rental newRental = rentalService.createRental(rentalDto);
        RentalResponse rentalResponse = new RentalResponse("Rental created!", newRental);
        return ResponseEntity.ok(rentalResponse);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RentalResponse> updateRental(
            @PathVariable Long id,
            @ModelAttribute RentalDto rentalDto) {
        Rental updatedRental = rentalService.updateRental(id, rentalDto);
        RentalResponse rentalResponse = new RentalResponse("Rental updated!", updatedRental);
        return ResponseEntity.ok(rentalResponse);
    }
}
