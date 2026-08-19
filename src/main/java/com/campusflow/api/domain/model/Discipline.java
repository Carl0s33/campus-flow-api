package com.campusflow.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = disciplines)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    private String code;
    private String teacher;
    private String color;

    @Builder.Default
    private Integer absences = 0;

    @Builder.Default
    private Integer workload = 60;

    private Double n1;
    private Double n2;
}
