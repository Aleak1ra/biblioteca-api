package com.app.bibliotecaAPI.user.controller;

import com.app.bibliotecaAPI.user.dto.UserRequestDTO;
import com.app.bibliotecaAPI.user.dto.UserResponseDTO;
import com.app.bibliotecaAPI.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO body) {
        return ResponseEntity.ok(userService.createUser(body));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO body) {
        return ResponseEntity.ok(userService.updateUser(id, body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
