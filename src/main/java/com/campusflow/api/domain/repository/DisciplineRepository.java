package com.campusflow.api.domain.repository;

import com.campusflow.api.domain.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, String> {
}
