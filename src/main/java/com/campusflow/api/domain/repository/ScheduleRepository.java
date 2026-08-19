package com.campusflow.api.domain.repository;

import com.campusflow.api.domain.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    List<Schedule> findByDayOfWeek(Integer dayOfWeek);
    List<Schedule> findByDisciplineId(String disciplineId);
}
