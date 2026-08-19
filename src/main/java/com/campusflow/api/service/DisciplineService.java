package com.campusflow.api.service;

import com.campusflow.api.domain.model.Discipline;
import com.campusflow.api.domain.repository.DisciplineRepository;
import com.campusflow.api.dto.DisciplineRequestDTO;
import com.campusflow.api.dto.DisciplineResponseDTO;
import com.campusflow.api.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository repository;

    public List<DisciplineResponseDTO> listAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public DisciplineResponseDTO getById(String id) {
        return toDTO(findEntityById(id));
    }

    public DisciplineResponseDTO create(DisciplineRequestDTO dto) {
        Discipline discipline = new Discipline();
        updateEntityFromDTO(discipline, dto);
        return toDTO(repository.save(discipline));
    }

    public DisciplineResponseDTO update(String id, DisciplineRequestDTO dto) {
        Discipline discipline = findEntityById(id);
        updateEntityFromDTO(discipline, dto);
        return toDTO(repository.save(discipline));
    }

    public DisciplineResponseDTO updateAbsences(String id, Integer absences) {
        Discipline discipline = findEntityById(id);
        if (absences != null) discipline.setAbsences(absences);
        return toDTO(repository.save(discipline));
    }

    public DisciplineResponseDTO updateGrades(String id, Double n1, Double n2) {
        Discipline discipline = findEntityById(id);
        if (n1 != null) discipline.setN1(n1);
        if (n2 != null) discipline.setN2(n2);
        return toDTO(repository.save(discipline));
    }

    public void delete(String id) {
        Discipline discipline = findEntityById(id);
        repository.delete(discipline);
    }

    public Discipline findEntityById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
    }

    private void updateEntityFromDTO(Discipline entity, DisciplineRequestDTO dto) {
        entity.setName(dto.name());
        entity.setCode(dto.code());
        entity.setTeacher(dto.teacher());
        entity.setColor(dto.color());
        if (dto.absences() != null) entity.setAbsences(dto.absences());
        if (dto.workload() != null) entity.setWorkload(dto.workload());
        entity.setPeriod(dto.period());
        entity.setN1(dto.n1());
        entity.setN2(dto.n2());
    }

    private DisciplineResponseDTO toDTO(Discipline discipline) {
        return new DisciplineResponseDTO(
                discipline.getId(),
                discipline.getName(),
                discipline.getCode(),
                discipline.getTeacher(),
                discipline.getColor(),
                discipline.getAbsences(),
                discipline.getWorkload(),
                discipline.getPeriod(),
                discipline.getN1(),
                discipline.getN2()
        );
    }
}
