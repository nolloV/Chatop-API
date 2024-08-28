package com.openclassrooms.chatop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(Integer id) { // Utiliser Integer au lieu de Long
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
}
