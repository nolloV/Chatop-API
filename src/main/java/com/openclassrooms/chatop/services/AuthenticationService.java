package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.LoginUserDto;
import com.openclassrooms.chatop.dtos.RegisterUserDto;
import com.openclassrooms.chatop.dtos.UserDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Indique que cette classe est un service Spring
@Service
public class AuthenticationService {

    // Dépôt pour gérer les opérations liées aux utilisateurs
    private final UserRepository userRepository;

    // Encodeur de mot de passe pour hacher les mots de passe des utilisateurs
    private final PasswordEncoder passwordEncoder;

    // Gestionnaire d'authentification pour authentifier les utilisateurs
    private final AuthenticationManager authenticationManager;

    // Service pour gérer les opérations liées aux JWT
    private final JwtService jwtService;

    // Constructeur pour injecter les dépendances
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Inscrire un nouvel utilisateur.
     *
     * @param input les informations de l'utilisateur à inscrire.
     * @return l'utilisateur inscrit.
     */
    public User signup(RegisterUserDto input) {
        // Crée un nouvel utilisateur avec les informations fournies et hache le mot de passe
        User user = new User()
                .setName(input.getName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));

        // Sauvegarde l'utilisateur dans le dépôt et retourne l'utilisateur sauvegardé
        return userRepository.save(user);
    }

    /**
     * Authentifier un utilisateur.
     *
     * @param input les informations de connexion de l'utilisateur.
     * @return l'utilisateur authentifié.
     */
    public User authenticate(LoginUserDto input) {
        // Authentifie l'utilisateur avec l'email et le mot de passe fournis
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        // Recherche l'utilisateur par email et le retourne, ou lance une exception si non trouvé
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    /**
     * Obtenir l'utilisateur actuellement authentifié.
     *
     * @return les informations de l'utilisateur actuellement authentifié.
     */
    public UserDto getCurrentUser() {
        // Obtient le principal (détails de l'utilisateur) du contexte de sécurité
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            // Si le principal est une instance de UserDetails, obtient l'email de l'utilisateur
            String email = userDetails.getUsername();
            // Recherche l'utilisateur par email et le retourne sous forme de DTO
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            UserDto userDto = new UserDto();
            userDto.setId(user.getId().longValue()); // Convertir l'ID en Long
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setUpdatedAt(user.getUpdatedAt());
            return userDto;
        } else {
            // Lance une exception si l'utilisateur n'est pas authentifié
            throw new RuntimeException("Utilisateur non authentifié");
        }
    }

    /**
     * Obtenir un utilisateur à partir d'un token JWT.
     *
     * @param token le token JWT.
     * @return l'utilisateur correspondant au token.
     */
    public User getUserFromToken(String token) {
        // Extrait l'email de l'utilisateur à partir du token
        String email = jwtService.extractUsername(token);
        // Recherche l'utilisateur par email et le retourne, ou lance une exception si non trouvé
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
}
