package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.models.Rental;
import com.openclassrooms.chatop.dtos.RentalDto;
import com.openclassrooms.chatop.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public Rental createRental(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setName(rentalDto.getName());
        rental.setDescription(rentalDto.getDescription());
        rental.setPrice(rentalDto.getPrice());
        rental.setSurface(rentalDto.getSurface());
        rental.setPicture(rentalDto.getPicture());
        rental.setOwnerId(rentalDto.getOwnerId());
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());
        return rentalRepository.save(rental);
    }

    public Rental updateRental(Long id, RentalDto rentalDto) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            rental.setName(rentalDto.getName());
            rental.setDescription(rentalDto.getDescription());
            rental.setPrice(rentalDto.getPrice());
            rental.setSurface(rentalDto.getSurface());
            rental.setPicture(rentalDto.getPicture());
            rental.setOwnerId(rentalDto.getOwnerId());
            rental.setUpdatedAt(LocalDateTime.now());
            return rentalRepository.save(rental);
        }
        return null;
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
