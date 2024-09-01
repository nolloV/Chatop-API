package com.openclassrooms.chatop.repositories;

import com.openclassrooms.chatop.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Indique que cette interface est un dépôt Spring pour l'entité User
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    // Méthode pour trouver un utilisateur par son adresse email
    Optional<User> findByEmail(String email);
}