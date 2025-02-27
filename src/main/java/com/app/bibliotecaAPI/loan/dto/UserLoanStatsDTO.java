package com.app.bibliotecaAPI.loan.dto;

import com.app.bibliotecaAPI.user.dto.UserResponseDTO;

public record UserLoanStatsDTO(
        UserResponseDTO user,
        Long loanCount
) {
}