package com.campusflow.api.domain.repository;

import com.campusflow.api.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByDisciplineId(String disciplineId);
}
