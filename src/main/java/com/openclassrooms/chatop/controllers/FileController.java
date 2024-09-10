package com.openclassrooms.chatop.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

// Déclare que cette classe est un contrôleur REST et mappe les requêtes à /static/upload
@RestController
@RequestMapping("/api/static/upload")
@SecurityRequirement(name = "bearerAuth")
public class FileController {

    // Chemin racine pour le stockage des fichiers uploadés
    private final Path rootLocation = Paths.get("src/main/resources/static/upload");

    // Endpoint pour gérer le téléchargement de fichiers
    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Vérifie si le fichier est vide
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to upload empty file.");
            }
            // Copie le fichier téléchargé dans le répertoire de destination
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            // Retourne une réponse de succès
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully.");
        } catch (IOException e) {
            // Retourne une réponse d'erreur en cas d'exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            // Résout le chemin du fichier demandé
            Path file = Paths.get("src/main/resources/static/upload/").resolve(filename);
            // Crée une ressource à partir du fichier
            Resource resource = new UrlResource(file.toUri());

            // Vérifie si la ressource existe et est lisible
            if (resource.exists() || resource.isReadable()) {
                // Retourne le fichier en tant que ressource avec les en-têtes appropriés
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                // Retourne une réponse 404 si le fichier n'est pas trouvé ou n'est pas lisible
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Retourne une réponse 500 en cas d'exception
            return ResponseEntity.internalServerError().build();
        }
    }
}
