package com.campusflow.api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disciplines")
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
    private Integer period;

    @Builder.Default
    private Integer absences = 0;

    @Builder.Default
    private Integer workload = 60;

    private Double n1;
    private Double n2;
    private Double recoveryGrade;

    @Builder.Default
    private Integer pomodoroCount = 0;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Exam> exams = new ArrayList<>();
}
