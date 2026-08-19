package com.campusflow.api.controller;

import com.campusflow.api.domain.model.Task;
import com.campusflow.api.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(/api/tasks)
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository repository;

    @GetMapping
    public List<Task> listAll(@RequestParam(required = false) String disciplineId) {
        if (disciplineId != null) {
            return repository.findByDisciplineId(disciplineId);
        }
        return repository.findAll();
    }

    @GetMapping(/{id})
    public ResponseEntity<Task> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return repository.save(task);
    }

    @PatchMapping(/{id}/toggle)
    public ResponseEntity<Task> toggle(@PathVariable String id) {
        return repository.findById(id)
                .map(task -> {
                    task.setCompleted(!Boolean.TRUE.equals(task.getCompleted()));
                    return ResponseEntity.ok(repository.save(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(/{id})
    public ResponseEntity<Task> update(@PathVariable String id, @RequestBody Task task) {
        return repository.findById(id)
                .map(existing -> {
                    task.setId(id);
                    return ResponseEntity.ok(repository.save(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(/{id})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
