package com.campusflow.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequestDTO(
    @NotBlank(message = O título é obrigatório)
    String title,
    String description,
    @NotBlank(message = A data de entrega é obrigatória)
    String dueDate,
    String type,
    String priority,
    Boolean completed,
    @NotBlank(message = A disciplina é obrigatória)
    String disciplineId
) {}
