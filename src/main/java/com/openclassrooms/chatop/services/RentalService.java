package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.models.Rental;
import com.openclassrooms.chatop.dtos.RentalDto;
import com.openclassrooms.chatop.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class RentalService {

    private static final Logger logger = Logger.getLogger(RentalService.class.getName());

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

        // Convertir le fichier en chaîne (par exemple, en enregistrant le fichier et en stockant le chemin)
        String picturePath = saveFile(rentalDto.getPicture());
        if (picturePath != null) {
            rental.setPicture(picturePath); // Utiliser une chaîne de caractères pour le champ picture
        }

        rental.setOwnerId(rentalDto.getOwnerId());
        // Les champs createdAt et updatedAt seront initialisés par @PrePersist
        return rentalRepository.save(rental);
    }

    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            logger.warning("Le fichier est nul ou vide.");
            return null;
        }

        try {
            String currentDir = System.getProperty("user.dir");
            String uploadDir = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "upload" + File.separator;
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            // Retourner l'URL relative
            return "/static/upload/" + file.getOriginalFilename();
        } catch (IOException e) {
            return null;
        }
    }

    public Rental updateRental(Long id, RentalDto rentalDto) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental != null) {
            rental.setName(rentalDto.getName());
            rental.setDescription(rentalDto.getDescription());
            rental.setPrice(rentalDto.getPrice());
            rental.setSurface(rentalDto.getSurface());

            // Convertir le fichier en chaîne (par exemple, en enregistrant le fichier et en stockant le chemin)
            String picturePath = saveFile(rentalDto.getPicture());
            if (picturePath != null) {
                rental.setPicture(picturePath); // Utiliser une chaîne de caractères pour le champ picture
            }

            rental.setOwnerId(rentalDto.getOwnerId());
            // Le champ updatedAt sera mis à jour par @PreUpdate
            return rentalRepository.save(rental);
        }
        return null;
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    // Nouvelle méthode pour obtenir les locations par propriétaire
    public List<Rental> getRentalsByOwnerId(Long ownerId) {
        return rentalRepository.findByOwnerId(ownerId);
    }
}