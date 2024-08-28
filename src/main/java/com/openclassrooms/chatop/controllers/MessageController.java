package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.MessageDto;
import com.openclassrooms.chatop.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageDto messageDto) {
        messageService.createMessage(messageDto);
        return ResponseEntity.ok("{\"message\": \"Message send with success\"}");
    }
}
