package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.dtos.LoginUserDto;
import com.openclassrooms.chatop.dtos.RegisterUserDto;
import com.openclassrooms.chatop.dtos.UserDto;
import com.openclassrooms.chatop.responses.LoginResponse;
import com.openclassrooms.chatop.services.AuthenticationService;
import com.openclassrooms.chatop.services.JwtService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

// Déclare que cette classe est un contrôleur REST et mappe les requêtes à /api/auth
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    // Constructeur pour injecter les services nécessaires
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    // Endpoint pour l'enregistrement des utilisateurs
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterUserDto registerUserDto) {
        // Inscrit un nouvel utilisateur
        User registeredUser = authenticationService.signup(registerUserDto);
        // Génère un token JWT pour l'utilisateur enregistré
        String jwtToken = jwtService.generateToken(registeredUser);
        // Crée une réponse contenant le token
        Map<String, String> response = new HashMap<>();
        response.put("token", jwtToken);
        // Retourne la réponse avec le token
        return ResponseEntity.ok(response);
    }

    // Endpoint pour l'authentification des utilisateurs
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        // Authentifie l'utilisateur
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        // Génère un token JWT pour l'utilisateur authentifié
        String jwtToken = jwtService.generateToken(authenticatedUser);
        // Crée une réponse contenant le token et le temps d'expiration
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        // Retourne la réponse avec le token et le temps d'expiration
        return ResponseEntity.ok(loginResponse);
    }

    // Endpoint pour obtenir les informations de l'utilisateur authentifié
    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String token) {
        // Vérifie si le token est présent et commence par "Bearer "
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.ok("User is not authenticated");
        }
        // Extrait le token sans le préfixe "Bearer "
        token = token.substring(7);
        // Récupère l'utilisateur à partir du token
        User currentUser = authenticationService.getUserFromToken(token);
        // Crée un DTO pour l'utilisateur
        UserDto userDto = new UserDto(currentUser.getName(), currentUser.getEmail(), currentUser.getCreatedAt(), currentUser.getUpdatedAt());
        // Retourne les informations de l'utilisateur
        return ResponseEntity.ok(userDto);
    }
}
