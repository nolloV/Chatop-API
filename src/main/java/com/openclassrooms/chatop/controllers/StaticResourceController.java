package com.openclassrooms.chatop.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.nio.file.Paths;

// Déclare que cette classe est un contrôleur et mappe les requêtes à /api/static/upload
@Controller
@RequestMapping("/api/static/upload")
public class StaticResourceController {

    // Endpoint pour servir les fichiers statiques
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