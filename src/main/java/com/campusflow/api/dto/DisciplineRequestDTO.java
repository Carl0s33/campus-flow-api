package com.campusflow.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record DisciplineRequestDTO(
    @NotBlank(message = "O nome da disciplina é obrigatório")
    String name,
    String code,
    String teacher,
    String color,
    @Min(value = 0, message = "Faltas não podem ser negativas")
    Integer absences,
    @Min(value = 1, message = "Carga horária deve ser maior que zero")
    Integer workload,
    Double n1,
    Double n2
) {}
