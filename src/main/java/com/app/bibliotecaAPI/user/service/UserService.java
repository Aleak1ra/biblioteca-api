package com.app.bibliotecaAPI.user.service;

import com.app.bibliotecaAPI.user.dto.UserRequestDTO;
import com.app.bibliotecaAPI.user.dto.UserResponseDTO;
import com.app.bibliotecaAPI.user.model.User;
import com.app.bibliotecaAPI.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO body) throws Exception {

        if(body.name() == null || body.name().isBlank()) {
            throw new Exception("O nome do usuário não pode ser vazio.");
        }
        if(body.email() == null || body.email().isBlank()) {
            throw new Exception("O email do usuário não pode ser vazio.");
        }


        User user = new User();
        user.setName(body.name());
        user.setEmail(body.email());
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO body) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        user.setName(body.name());
        user.setEmail(body.email());
        User updatedUser = userRepository.save(user);

        return new UserResponseDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        userRepository.delete(user);
    }
}
