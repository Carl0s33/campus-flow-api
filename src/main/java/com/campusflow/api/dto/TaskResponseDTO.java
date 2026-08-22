package com.campusflow.api.dto;

public record TaskResponseDTO(
    String id,
    String title,
    String description,
    String dueDate,
    String type,
    String priority,
    Boolean completed,
    String disciplineId,
    String disciplineName,
    Integer pomodoroCount
) {}
