package com.campusflow.api.dto;

public record ScheduleResponseDTO(
    String id,
    String disciplineId,
    String disciplineName,
    Integer dayOfWeek,
    String startTime,
    String endTime,
    String room
) {}
