package com.campusflow.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = schedules)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String disciplineId;

    @Column(nullable = false)
    private Integer dayOfWeek;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    private String room;
}
