package com.campusflow.api.controller;

import com.campusflow.api.dto.ScheduleRequestDTO;
import com.campusflow.api.dto.ScheduleResponseDTO;
import com.campusflow.api.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(/api/schedules)
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping
    public List<ScheduleResponseDTO> listAll(@RequestParam(required = false) Integer dayOfWeek) {
        return service.listAll(dayOfWeek);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponseDTO create(@Valid @RequestBody ScheduleRequestDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping(/{id})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
