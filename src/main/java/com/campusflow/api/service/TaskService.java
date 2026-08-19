package com.campusflow.api.service;

import com.campusflow.api.domain.model.Discipline;
import com.campusflow.api.domain.model.Task;
import com.campusflow.api.domain.repository.TaskRepository;
import com.campusflow.api.dto.TaskRequestDTO;
import com.campusflow.api.dto.TaskResponseDTO;
import com.campusflow.api.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final DisciplineService disciplineService;

    public List<TaskResponseDTO> listAll(String disciplineId) {
        List<Task> tasks = (disciplineId != null) 
                ? repository.findByDisciplineId(disciplineId)
                : repository.findAll();
        return tasks.stream().map(this::toDTO).toList();
    }

    public TaskResponseDTO getById(String id) {
        return toDTO(findEntityById(id));
    }

    public TaskResponseDTO create(TaskRequestDTO dto) {
        Discipline discipline = disciplineService.findEntityById(dto.disciplineId());
        Task task = new Task();
        updateEntityFromDTO(task, dto, discipline);
        return toDTO(repository.save(task));
    }

    public TaskResponseDTO update(String id, TaskRequestDTO dto) {
        Task task = findEntityById(id);
        Discipline discipline = disciplineService.findEntityById(dto.disciplineId());
        updateEntityFromDTO(task, dto, discipline);
        return toDTO(repository.save(task));
    }

    public TaskResponseDTO toggle(String id) {
        Task task = findEntityById(id);
        task.setCompleted(!Boolean.TRUE.equals(task.getCompleted()));
        return toDTO(repository.save(task));
    }

    public void delete(String id) {
        Task task = findEntityById(id);
        repository.delete(task);
    }

    private Task findEntityById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Tarefa não encontrada));
    }

    private void updateEntityFromDTO(Task entity, TaskRequestDTO dto, Discipline discipline) {
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setDueDate(dto.dueDate());
        entity.setType(dto.type());
        entity.setPriority(dto.priority());
        if (dto.completed() != null) entity.setCompleted(dto.completed());
        entity.setDiscipline(discipline);
    }

    private TaskResponseDTO toDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getType(),
                task.getPriority(),
                task.getCompleted(),
                task.getDiscipline().getId(),
                task.getDiscipline().getName()
        );
    }
}
