package com.campusflow.api.controller;

import com.campusflow.api.domain.model.Schedule;
import com.campusflow.api.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(/api/schedules)
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRepository repository;

    @GetMapping
    public List<Schedule> listAll(@RequestParam(required = false) Integer dayOfWeek) {
        if (dayOfWeek != null) {
            return repository.findByDayOfWeek(dayOfWeek);
        }
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule create(@RequestBody Schedule schedule) {
        return repository.save(schedule);
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
