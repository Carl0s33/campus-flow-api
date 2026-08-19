package com.campusflow.api.dto;

public record ExamResponseDTO(
    String id,
    String disciplineId,
    String disciplineName,
    String title,
    String date,
    String time,
    String topics,
    String location
) {}
