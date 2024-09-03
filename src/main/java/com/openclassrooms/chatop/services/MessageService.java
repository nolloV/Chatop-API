package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.MessageDto;
import com.openclassrooms.chatop.models.Message;
import com.openclassrooms.chatop.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Récupérer tous les messages.
     *
     * @return une liste de tous les messages.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Récupérer un message par son ID.
     *
     * @param id l'ID du message à récupérer.
     * @return un Optional contenant le message s'il est trouvé, sinon un
     * Optional vide.
     */
    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    /**
     * Créer un nouveau message à partir d'un DTO.
     *
     * @param messageDto le DTO contenant les informations du message à créer.
     * @return le message créé et sauvegardé.
     */
    public Message createMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getMessage());
        message.setSender(authenticationService.getCurrentUser().getName()); // Utiliser le nom de l'utilisateur courant
        message.setUserId(authenticationService.getCurrentUser().getId()); // Utiliser l'ID de l'utilisateur courant
        message.setRentalId(messageDto.getRental_id());
        message.setCreatedAt(LocalDateTime.now()); // Définir la date de création actuelle
        message.setUpdatedAt(LocalDateTime.now()); // Définir la date de mise à jour actuelle
        return messageRepository.save(message); // Sauvegarder le message dans la base de données
    }

    /**
     * Mettre à jour un message existant.
     *
     * @param id l'ID du message à mettre à jour.
     * @param messageDto le DTO contenant les nouvelles informations du message.
     * @return le message mis à jour et sauvegardé.
     */
    public Message updateMessage(Long id, MessageDto messageDto) {
        // Trouver le message par son ID ou lancer une exception s'il n'est pas trouvé
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContent(messageDto.getMessage());
        message.setSender(authenticationService.getCurrentUser().getName()); // Utiliser le nom de l'utilisateur courant
        message.setUserId(authenticationService.getCurrentUser().getId()); // Utiliser l'ID de l'utilisateur courant
        message.setRentalId(messageDto.getRental_id());
        message.setUpdatedAt(LocalDateTime.now()); // Définir la date de mise à jour actuelle
        return messageRepository.save(message); // Sauvegarder le message mis à jour dans la base de données
    }
}
