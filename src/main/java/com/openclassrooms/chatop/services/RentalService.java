package com.openclassrooms.chatop.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.chatop.dtos.RentalDto;
import com.openclassrooms.chatop.dtos.UserDto;
import com.openclassrooms.chatop.entities.Rental;
import com.openclassrooms.chatop.exceptions.UnauthorizedException;
import com.openclassrooms.chatop.repositories.RentalRepository;

// Indique que cette classe est un service Spring
@Service
public class RentalService {

    // Logger pour enregistrer les messages d'information et d'erreur
    private static final Logger logger = Logger.getLogger(RentalService.class.getName());

    // Dépôt pour gérer les opérations liées aux locations
    @Autowired
    private RentalRepository rentalRepository;

    // Service pour gérer l'authentification
    @Autowired
    private AuthenticationService authenticationService;

// Retourne toutes les locations
    public List<Rental> getAllRentals() {
        // Récupère toutes les locations depuis le dépôt
        List<Rental> rentals = rentalRepository.findAll();

        // Récupère l'utilisateur actuellement authentifié
        UserDto currentUser = authenticationService.getCurrentUser();
        Integer currentUserId = currentUser.getId();

        // Parcourt chaque location et définit l'ID de l'utilisateur actuel
        for (Rental rental : rentals) {
            rental.setUserId(currentUserId);
            logger.info("Rental ownerId: " + rental.getOwnerId() + ", userId: " + rental.getUserId());
        }

        // Retourne la liste des locations mises à jour
        return rentals;
    }

    // Retourne une location par son identifiant
    public Rental getRentalById(Integer id) {
        return rentalRepository.findById(id).orElse(null);
    }

    // Crée une nouvelle location à partir d'un DTO et l'ajoute au dépôt
    public Rental createRental(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setName(rentalDto.getName());
        rental.setDescription(rentalDto.getDescription());
        rental.setPrice(rentalDto.getPrice());
        rental.setSurface(rentalDto.getSurface());

        // Convertir le fichier en chaîne (par exemple, en enregistrant le fichier et en stockant le chemin)
        String picturePath = saveFile(rentalDto.getPicture());
        if (picturePath != null) {
            rental.setPicture(picturePath); // Utiliser une chaîne de caractères pour le champ picture
        }

        // Obtenir l'utilisateur actuellement authentifié
        UserDto currentUser = authenticationService.getCurrentUser();
        rental.setOwnerId(currentUser.getId());

        // Les champs createdAt et updatedAt seront initialisés par @PrePersist
        return rentalRepository.save(rental);
    }

    // Méthode pour enregistrer un fichier sur le disque et retourner son chemin
    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            logger.warning("Le fichier est nul ou vide.");
            return null;
        }

        try {
            // Détermine le répertoire de téléchargement
            String currentDir = System.getProperty("user.dir");
            String uploadDir = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "upload" + File.separator;
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Enregistre le fichier dans le répertoire de téléchargement
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            // Retourner l'URL relative
            return "/static/upload/" + file.getOriginalFilename();
        } catch (IOException e) {
            return null;
        }
    }

    // Met à jour une location existante à partir d'un DTO
    public Rental updateRental(Integer id, RentalDto rentalDto) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            // Obtenir l'utilisateur actuellement authentifié
            UserDto currentUser = authenticationService.getCurrentUser();

            // Vérifier si l'utilisateur authentifié est le propriétaire de la location
            if (!rental.getOwnerId().equals(currentUser.getId())) {
                throw new UnauthorizedException("Vous n'êtes pas autorisé à mettre à jour cette location.");
            }

            rental.setName(rentalDto.getName());
            rental.setDescription(rentalDto.getDescription());
            rental.setPrice(rentalDto.getPrice());
            rental.setSurface(rentalDto.getSurface());

            // Convertir le fichier en chaîne (par exemple, en enregistrant le fichier et en stockant le chemin)
            String picturePath = saveFile(rentalDto.getPicture());
            if (picturePath != null) {
                rental.setPicture(picturePath); // Utiliser une chaîne de caractères pour le champ picture
            }

            // Le champ updatedAt sera mis à jour par @PreUpdate
            return rentalRepository.save(rental);
        }
        return null;
    }

    // Supprime une location par son identifiant
    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }

    // Nouvelle méthode pour obtenir les locations par propriétaire
    public List<Rental> getRentalsByOwnerId(Integer ownerId) {
        return rentalRepository.findByOwnerId(ownerId);
    }
}
