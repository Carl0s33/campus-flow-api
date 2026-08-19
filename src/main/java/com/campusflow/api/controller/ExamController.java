package com.campusflow.api.controller;

import com.campusflow.api.domain.model.Exam;
import com.campusflow.api.domain.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(/api/exams)
@RequiredArgsConstructor
public class ExamController {

    private final ExamRepository repository;

    @GetMapping
    public List<Exam> listAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exam create(@RequestBody Exam exam) {
        return repository.save(exam);
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
