package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    // Nouvelle méthode pour trouver les locations par ownerId
    List<Rental> findByOwnerId(Long ownerId);
}
