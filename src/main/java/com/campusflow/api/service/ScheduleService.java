package com.campusflow.api.service;

import com.campusflow.api.domain.model.Discipline;
import com.campusflow.api.domain.model.Schedule;
import com.campusflow.api.domain.repository.ScheduleRepository;
import com.campusflow.api.dto.ScheduleRequestDTO;
import com.campusflow.api.dto.ScheduleResponseDTO;
import com.campusflow.api.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final DisciplineService disciplineService;

    public List<ScheduleResponseDTO> listAll(Integer dayOfWeek) {
        List<Schedule> schedules = (dayOfWeek != null) 
                ? repository.findByDayOfWeek(dayOfWeek)
                : repository.findAll();
        return schedules.stream().map(this::toDTO).toList();
    }

    public ScheduleResponseDTO create(ScheduleRequestDTO dto) {
        Discipline discipline = disciplineService.findEntityById(dto.disciplineId());
        Schedule schedule = new Schedule();
        schedule.setDayOfWeek(dto.dayOfWeek());
        schedule.setStartTime(dto.startTime());
        schedule.setEndTime(dto.endTime());
        schedule.setRoom(dto.room());
        schedule.setDiscipline(discipline);
        return toDTO(repository.save(schedule));
    }

    public void delete(String id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Horário não encontrado));
        repository.delete(schedule);
    }

    private ScheduleResponseDTO toDTO(Schedule schedule) {
        return new ScheduleResponseDTO(
                schedule.getId(),
                schedule.getDiscipline().getId(),
                schedule.getDiscipline().getName(),
                schedule.getDayOfWeek(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getRoom()
        );
    }
}
