package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.MessageDto;
import com.openclassrooms.chatop.models.Message;
import com.openclassrooms.chatop.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // Endpoint pour récupérer tous les messages
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    // Endpoint pour créer un nouveau message
    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody MessageDto messageDto) {
        // Convertir le DTO en entité Message
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setSender(messageDto.getSender());
        message.setUserId(messageDto.getUserId());
        message.setRentalId(messageDto.getRentalId());

        // Créer le message en utilisant le service
        messageService.createMessage(message);

        // Retourner le message de succès au format JSON
        return ResponseEntity.ok("{\"message\": \"Message sent with success\"}");
    }
}