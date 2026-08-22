package com.campusflow.api.dto;

public record LoginResponseDTO(
        String id,
        String name,
        String matricula,
        String email,
        String photoUrl
) {}
