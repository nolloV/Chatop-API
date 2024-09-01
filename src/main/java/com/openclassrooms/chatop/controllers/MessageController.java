package com.openclassrooms.chatop.controllers;

import com.openclassrooms.chatop.dtos.MessageDto;
import com.openclassrooms.chatop.models.Message;
import com.openclassrooms.chatop.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message createMessage(@RequestBody MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setSender(messageDto.getSender());
        message.setUser_id(messageDto.getUser_id());
        message.setRental_id(messageDto.getRental_id());
        message.setTimestamp(LocalDateTime.now());
        return messageService.createMessage(message);
    }
}
