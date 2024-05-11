package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.mapper.AgrMapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.AgrService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.BranchService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.CellService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgrController {
    private final AgrService service;
    private final CellService cellService;
    private final AgrMapper mapper;

    @Operation(summary = "Создание гардеробного ряда")
    @PostMapping("/agr")
    public AgrCompactResponse createAgr(@RequestBody @Valid AgrCreateRequest request){
        Agr agr = service.create(mapper.fromCreateRequest(request));
        return mapper.toCompactResponse(agr);
    }

    @Operation(summary = "Получение полных данных гардеробного ряда")
    @GetMapping("/agr/{agrId}")
    public AgrFullResponse getFullAgr(@PathVariable Long agrId){
        Agr agr = service.getByIdStrict(agrId);
        return mapper.toFullResponse(agr, cellService.getCellsByAgrId(agr.getId()));
    }

    @Operation(summary = "Получение списка гардеробных рядов")
    @GetMapping("/agrs")
    public List<AgrCompactResponse> getAll(){
        var agrs = service.getAll();
        return mapper.toListCompactResponse(agrs);
    }

    @Operation(summary = "Получение фильтрованного списка гардеробных рядов")
    @PostMapping("/agrs/filter")
    public List<AgrCompactResponse> getByFilter(@RequestBody @Valid AgrFilter filter){
        var agrs = service.getByFilter(filter);
        return mapper.toListCompactResponse(agrs);
    }

    @Operation(summary = "Обновление данных гардеробного ряда")
    @PutMapping("/agr/{agrId}")
    public AgrCompactResponse updateAgr(@PathVariable Long agrId, @RequestBody @Valid AgrUpdateRequest request){
        Agr agr = service.update(agrId, mapper.fromUpdateRequest(request));
        return mapper.toCompactResponse(agr);
    }

    @Operation(summary = "Удаление данных гардеробного ряда")
    @DeleteMapping("/agr/{agrId}")
    public void deleteSafe(@PathVariable Long agrId){
        service.deleteByIdSoft(agrId);
    }
}
