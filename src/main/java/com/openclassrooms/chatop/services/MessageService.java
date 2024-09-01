package com.openclassrooms.chatop.services;

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

    public Message createMessage(Message message) {
        message.setId((long) (messages.size() + 1));
        message.setTimestamp(LocalDateTime.now());
        messages.add(message);
        return message;
    }
}