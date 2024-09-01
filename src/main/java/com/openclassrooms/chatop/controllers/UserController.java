package com.openclassrooms.chatop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dtos.UserDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.UserService;

// Déclare que cette classe est un contrôleur REST et mappe les requêtes à /api/user
@RequestMapping("/api/user")
@RestController
public class UserController {

    // Service pour gérer les opérations liées aux utilisateurs
    private final UserService userService;

    // Constructeur pour injecter le service UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint pour obtenir un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        // Récupère l'utilisateur par son ID via le service
        User user = userService.getUserById(id);
        // Convertit l'utilisateur en DTO pour la réponse
        UserDto userDto = new UserDto(user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
        // Retourne l'utilisateur dans une réponse HTTP
        return ResponseEntity.ok(userDto);
    }
}