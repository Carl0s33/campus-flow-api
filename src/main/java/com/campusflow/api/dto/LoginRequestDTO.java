package com.campusflow.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Login pode ser feito com email OU matrícula
public record LoginRequestDTO(
        // Campo usado como identificador: email ou matrícula
        @NotBlank(message = "Email ou matrícula é obrigatório")
        String identifier,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 4, message = "Senha deve ter pelo menos 4 caracteres")
        String password
) {}
