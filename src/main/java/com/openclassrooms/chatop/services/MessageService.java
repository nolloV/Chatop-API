package com.openclassrooms.chatop.services;

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

    // Créer un nouveau message
    public Message createMessage(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Mettre à jour un message existant
    public Message updateMessage(Long id, Message messageDetails) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContent(messageDetails.getContent());
        message.setUpdatedAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    // Supprimer un message par son ID
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}