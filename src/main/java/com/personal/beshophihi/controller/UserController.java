package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.LoginDTO;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.dto.UserDTO;
import com.personal.beshophihi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllUsers() {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all users successfully.")
                        .data(userService.getUsers())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetUserById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched user with ID: " + id + " successfully.")
                        .data(userService.getUserById(id))
                        .build(),
                HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<ResponseMessage> doGetByUsername(@PathVariable String username) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .status("TRUE")
                .message("Fetched username successfully.")
                .data(userService.getUserByUserName(username))
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("User created successfully.")
                        .data(userService.addNewUser(userDTO))
                        .build(),
                HttpStatus.CREATED);
    }


}
