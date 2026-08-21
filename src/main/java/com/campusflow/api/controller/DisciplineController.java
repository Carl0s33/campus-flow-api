package com.campusflow.api.controller;

import com.campusflow.api.dto.DisciplineRequestDTO;
import com.campusflow.api.dto.DisciplineResponseDTO;
import com.campusflow.api.service.DisciplineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService service;

    @GetMapping
    public List<DisciplineResponseDTO> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public DisciplineResponseDTO getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DisciplineResponseDTO create(@Valid @RequestBody DisciplineRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public DisciplineResponseDTO update(@PathVariable String id, @Valid @RequestBody DisciplineRequestDTO dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/{id}/absences")
    public DisciplineResponseDTO updateAbsences(@PathVariable String id, @RequestBody Map<String, Integer> payload) {
        return service.updateAbsences(id, payload.get("absences"));
    }

    @PatchMapping("/{id}/grades")
    public DisciplineResponseDTO updateGrades(@PathVariable String id, @RequestBody Map<String, Double> payload) {
        return service.updateGrades(id, payload.get("n1"), payload.get("n2"), payload.get("recoveryGrade"));
    }

    @PatchMapping("/{id}/pomodoro")
    public DisciplineResponseDTO incrementPomodoro(@PathVariable String id) {
        return service.incrementPomodoro(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
