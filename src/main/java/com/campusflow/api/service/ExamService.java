package com.campusflow.api.service;

import com.campusflow.api.domain.model.Discipline;
import com.campusflow.api.domain.model.Exam;
import com.campusflow.api.domain.repository.ExamRepository;
import com.campusflow.api.dto.ExamRequestDTO;
import com.campusflow.api.dto.ExamResponseDTO;
import com.campusflow.api.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository repository;
    private final DisciplineService disciplineService;

    public List<ExamResponseDTO> listAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public ExamResponseDTO create(ExamRequestDTO dto) {
        Discipline discipline = disciplineService.findEntityById(dto.disciplineId());
        Exam exam = new Exam();
        exam.setTitle(dto.title());
        exam.setDate(dto.date());
        exam.setTime(dto.time());
        exam.setTopics(dto.topics());
        exam.setLocation(dto.location());
        exam.setDiscipline(discipline);
        return toDTO(repository.save(exam));
    }

    public void delete(String id) {
        Exam exam = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prova não encontrada"));
        repository.delete(exam);
    }

    private ExamResponseDTO toDTO(Exam exam) {
        return new ExamResponseDTO(
                exam.getId(),
                exam.getDiscipline().getId(),
                exam.getDiscipline().getName(),
                exam.getTitle(),
                exam.getDate(),
                exam.getTime(),
                exam.getTopics(),
                exam.getLocation()
        );
    }
}
