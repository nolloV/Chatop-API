package com.openclassrooms.chatop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;

// Indique que cette classe est un service Spring
@Service
public class UserService {

    // Dépôt pour gérer les opérations liées aux utilisateurs
    private final UserRepository userRepository;

    // Constructeur pour injecter le dépôt UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retourne tous les utilisateurs
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        // Ajoute tous les utilisateurs trouvés dans le dépôt à la liste
        userRepository.findAll().forEach(users::add);
        return users;
    }

    // Retourne un utilisateur par son identifiant
    public User getUserById(Integer id) {
        // Recherche l'utilisateur par identifiant
        Optional<User> user = userRepository.findById(id);
        // Retourne l'utilisateur s'il est trouvé, sinon retourne null
        return user.orElse(null);
    }
}
