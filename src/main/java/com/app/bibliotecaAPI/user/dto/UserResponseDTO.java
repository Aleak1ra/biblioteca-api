package com.app.bibliotecaAPI.user.dto;

import com.app.bibliotecaAPI.user.model.User;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }

}
