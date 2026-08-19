package com.campusflow.api.controller;

import com.campusflow.api.dto.TaskRequestDTO;
import com.campusflow.api.dto.TaskResponseDTO;
import com.campusflow.api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<TaskResponseDTO> listAll(@RequestParam(required = false) String disciplineId) {
        return service.listAll(disciplineId);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(@Valid @RequestBody TaskRequestDTO dto) {
        return service.create(dto);
    }

    @PatchMapping("/{id}/toggle")
    public TaskResponseDTO toggle(@PathVariable String id) {
        return service.toggle(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable String id, @Valid @RequestBody TaskRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
