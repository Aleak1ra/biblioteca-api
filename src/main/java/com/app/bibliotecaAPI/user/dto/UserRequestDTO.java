package com.app.bibliotecaAPI.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank(message = "O nome do usuário não pode ser nulo ou vazio") String name, @NotBlank(message = "o email do usuário não pode ser nulo ou vazio") String email) {
}
