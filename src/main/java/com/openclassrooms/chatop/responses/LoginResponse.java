package com.openclassrooms.chatop.responses;

// Classe représentant la réponse de connexion
public class LoginResponse {

    // Jeton d'authentification
    private String token;

    // Durée d'expiration du jeton en secondes
    private long expiresIn;

    // Getter pour le jeton d'authentification
    public String getToken() {
        return token;
    }

    // Setter pour le jeton d'authentification
    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    // Getter pour la durée d'expiration du jeton
    public long getExpiresIn() {
        return expiresIn;
    }

    // Setter pour la durée d'expiration du jeton
    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    // Méthode toString pour afficher les informations de la réponse de connexion
    @Override
    public String toString() {
        return "LoginResponse{"
                + "token='" + token + '\''
                + ", expiresIn=" + expiresIn
                + '}';
    }
}