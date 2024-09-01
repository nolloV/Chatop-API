package com.openclassrooms.chatop.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
@RequestMapping("/static/upload")
@SecurityRequirement(name = "bearerAuth")
public class FileController {

    // Loader de ressources pour charger les fichiers
    private final ResourceLoader resourceLoader;

    // Chemin racine pour le stockage des fichiers uploadés
    private final Path rootLocation = Paths.get("src/main/resources/static/upload");

    // Constructeur pour injecter le ResourceLoader
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

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

    // Endpoint pour servir les fichiers téléchargés
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            // Résout le chemin du fichier demandé
            Path file = rootLocation.resolve(filename);
            // Charge le fichier en tant que ressource
            Resource resource = resourceLoader.getResource("file:" + file.toString());

            // Prépare les en-têtes de la réponse
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

            // Retourne le fichier en tant que ressource avec les en-têtes appropriés
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            // Retourne une réponse 404 si le fichier n'est pas trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
