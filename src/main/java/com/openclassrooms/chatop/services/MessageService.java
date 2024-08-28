package com.openclassrooms.chatop.services;

import com.openclassrooms.chatop.dtos.MessageDto;
import com.openclassrooms.chatop.models.Message;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final List<Message> messages = new ArrayList<>();

    public List<Message> getAllMessages() {
        return messages;
    }

    public Message createMessage(MessageDto messageDto) {
        Message newMessage = new Message();
        newMessage.setId((long) (messages.size() + 1));
        newMessage.setContent(messageDto.getContent());
        newMessage.setSender(messageDto.getSender());
        newMessage.setTimestamp(LocalDateTime.now());
        messages.add(newMessage);
        return newMessage;
    }
}
