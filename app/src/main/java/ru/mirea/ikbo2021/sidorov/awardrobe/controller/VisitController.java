package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitFullResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Visit;
import ru.mirea.ikbo2021.sidorov.awardrobe.mapper.VisitMapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.VisitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitController {
    private final VisitService service;
    private final VisitMapper mapper;

    @Operation(summary = "Получение визита")
    @GetMapping("/visit/{visitId}")
    public VisitFullResponse getFullVisit(@PathVariable Long visitId){
        Visit visit = service.getByIdStrict(visitId);
        return mapper.toFullResponse(visit);
    }

    @Operation(summary = "Получение визитов с фильтрами")
    @PostMapping("/visits/filter")
    public List<VisitCompactResponse> getVisitsWithFilter(@RequestBody @Valid VisitFilter filter){
        List<Visit> visits = service.getByFilter(filter);
        return mapper.toListCompactResponse(visits);
    }

    @Operation(summary = "Получение списка всех визитов")
    @GetMapping("/visits")
    public List<VisitCompactResponse> getAll(){
        List<Visit> visits = service.getAll();
        return mapper.toListCompactResponse(visits);
    }
}
