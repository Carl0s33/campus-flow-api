package com.campusflow.api.dto;

import jakarta.validation.constraints.NotBlank;

public record ExamRequestDTO(
    @NotBlank(message = "A disciplina é obrigatória")
    String disciplineId,
    @NotBlank(message = "O título da prova é obrigatório")
    String title,
    @NotBlank(message = "A data da prova é obrigatória")
    String date,
    String time,
    String topics,
    String location
) {}
