package com.campusflow.api.dto;

public record DisciplineResponseDTO(
    String id,
    String name,
    String code,
    String teacher,
    String color,
    Integer absences,
    Integer workload,
    Integer period,
    Double n1,
    Double n2
) {}
