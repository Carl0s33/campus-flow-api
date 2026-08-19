package com.campusflow.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = exams)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String date;

    private String time;
    private String topics;
    private String location;
}
