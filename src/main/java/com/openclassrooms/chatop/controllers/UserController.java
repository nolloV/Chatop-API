package com.openclassrooms.chatop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dtos.UserDto;
import com.openclassrooms.chatop.entities.User;
import com.openclassrooms.chatop.services.UserService;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        UserDto userDto = new UserDto(user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
        return ResponseEntity.ok(userDto);
    }
}