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

    public DisciplineResponseDTO updateGrades(String id, Double n1, Double n2, Double recoveryGrade) {
        Discipline discipline = findEntityById(id);
        if (n1 != null) discipline.setN1(n1);
        if (n2 != null) discipline.setN2(n2);
        if (recoveryGrade != null) discipline.setRecoveryGrade(recoveryGrade);
        return toDTO(repository.save(discipline));
    }

    public DisciplineResponseDTO incrementPomodoro(String id) {
        Discipline discipline = findEntityById(id);
        discipline.setPomodoroCount(discipline.getPomodoroCount() + 1);
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
        entity.setRecoveryGrade(dto.recoveryGrade());
    }

    private DisciplineResponseDTO toDTO(Discipline discipline) {
        Double n1 = discipline.getN1();
        Double n2 = discipline.getN2();
        Double recoveryGrade = discipline.getRecoveryGrade();
        
        Double finalGrade = 0.0;
        String statusText = "Sem notas lançadas";
        String statusColor = "#6B7280";
        Boolean isApproved = false;
        Boolean inRecovery = false;

        if (n1 != null && n2 == null) {
            double requiredN2 = (30.0 - n1 * 2) / 3.0;
            if (requiredN2 <= 10.0) {
                statusText = String.format("Precisa de %.1f na N2", requiredN2).replace(",", ".");
                statusColor = "#F59E0B";
            } else {
                statusText = "Reprovado (N2 inviável)";
                statusColor = "#EF4444";
            }
        } else if (n1 != null && n2 != null) {
            double media = (n1 * 2 + n2 * 3) / 5.0;
            if (media < 6.0 && recoveryGrade != null) {
                media = (n1 * 2 + recoveryGrade * 3) / 5.0;
            }
            finalGrade = Math.round(media * 100.0) / 100.0;
            isApproved = finalGrade >= 6.0;
            inRecovery = finalGrade < 6.0 && recoveryGrade == null;

            if (isApproved) {
                statusText = String.format("Média: %.1f - Aprovado", finalGrade).replace(",", ".");
                statusColor = "#10B981";
            } else if (inRecovery) {
                double requiredRec = (30.0 - n1 * 2) / 3.0;
                statusText = String.format("Média: %.1f - Precisa de %.1f na Final", finalGrade, requiredRec).replace(",", ".");
                statusColor = "#F59E0B";
            } else {
                statusText = String.format("Média: %.1f - Reprovado", finalGrade).replace(",", ".");
                statusColor = "#EF4444";
            }
        }

        return new DisciplineResponseDTO(
                discipline.getId(),
                discipline.getName(),
                discipline.getCode(),
                discipline.getTeacher(),
                discipline.getColor(),
                discipline.getAbsences(),
                discipline.getWorkload(),
                discipline.getPeriod(),
                n1,
                n2,
                recoveryGrade,
                discipline.getPomodoroCount(),
                finalGrade,
                statusText,
                statusColor,
                isApproved,
                inRecovery
        );
    }
}
