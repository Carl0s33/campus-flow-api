package com.campusflow.api.controller;

import com.campusflow.api.domain.model.Discipline;
import com.campusflow.api.domain.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(/api/disciplines)
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineRepository repository;

    @GetMapping
    public List<Discipline> listAll() {
        return repository.findAll();
    }

    @GetMapping(/{id})
    public ResponseEntity<Discipline> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Discipline create(@RequestBody Discipline discipline) {
        return repository.save(discipline);
    }

    @PutMapping(/{id})
    public ResponseEntity<Discipline> update(@PathVariable String id, @RequestBody Discipline discipline) {
        return repository.findById(id)
                .map(existing -> {
                    discipline.setId(id);
                    return ResponseEntity.ok(repository.save(discipline));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(/{id}/absences)
    public ResponseEntity<Discipline> updateAbsences(@PathVariable String id, @RequestBody Map<String, Integer> payload) {
        return repository.findById(id)
                .map(discipline -> {
                    if (payload.containsKey(absences)) {
                        discipline.setAbsences(payload.get(absences));
                    }
                    return ResponseEntity.ok(repository.save(discipline));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(/{id}/grades)
    public ResponseEntity<Discipline> updateGrades(@PathVariable String id, @RequestBody Map<String, Double> payload) {
        return repository.findById(id)
                .map(discipline -> {
                    if (payload.containsKey(n1)) discipline.setN1(payload.get(n1));
                    if (payload.containsKey(n2)) discipline.setN2(payload.get(n2));
                    return ResponseEntity.ok(repository.save(discipline));
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
