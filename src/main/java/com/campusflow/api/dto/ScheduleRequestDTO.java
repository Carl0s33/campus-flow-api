package com.campusflow.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScheduleRequestDTO(
    @NotBlank(message = "A disciplina é obrigatória")
    String disciplineId,
    @NotNull(message = "O dia da semana é obrigatório")
    Integer dayOfWeek,
    @NotBlank(message = "O horário de início é obrigatório")
    String startTime,
    @NotBlank(message = "O horário de término é obrigatório")
    String endTime,
    String room
) {}
