package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Indique que cette interface est un dépôt Spring pour l'entité Rental
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    // Méthode pour trouver les locations par ownerId
    List<Rental> findByOwnerId(Long ownerId);
}
