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

    // Récupérer tous les messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Récupérer un message par son ID
    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    // Créer un nouveau message à partir d'un DTO
    public Message createMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getMessage()); // Utiliser le nouveau nom de champ
        message.setSender(messageDto.getSender());
        message.setUserId(messageDto.getUser_id()); // Utiliser le nouveau nom de champ
        message.setRentalId(messageDto.getRental_id()); // Utiliser le nouveau nom de champ
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Mettre à jour un message existant
    public Message updateMessage(Long id, MessageDto messageDto) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContent(messageDto.getMessage()); // Utiliser le nouveau nom de champ
        message.setSender(messageDto.getSender());
        message.setUserId(messageDto.getUser_id()); // Utiliser le nouveau nom de champ
        message.setRentalId(messageDto.getRental_id()); // Utiliser le nouveau nom de champ
        message.setUpdatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Supprimer un message par son ID
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
