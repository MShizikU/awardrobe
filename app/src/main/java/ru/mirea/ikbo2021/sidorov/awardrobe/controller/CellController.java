package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;
import ru.mirea.ikbo2021.sidorov.awardrobe.mapper.CellMapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.CellService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CellController {
    private final CellService service;
    private final CellMapper mapper;

    @Operation(summary = "Создание ячейки")
    @PostMapping("/cell")
    public CellCompactResponse createCell(@RequestBody @Valid CellCreateRequest request){
        Cell cell = service.create(mapper.fromCreateRequest(request));
        return mapper.toCompactResponse(cell);
    }

    @Operation(summary = "Создание множества ячеек")
    @PostMapping("/cells")
    public List<CellCompactResponse> createCells(@RequestBody @Valid CellMultipleCreateRequest request){
        List<Cell> cells = service.createMultiple(request);
        return mapper.toListCompactResponse(cells);
    }

    @Operation(summary = "Получение полных данных ячейки")
    @GetMapping("/cell/{cellId}")
    public CellFullResponse getFullCell(@PathVariable Long cellId){
        Cell cell = service.getByIdStrict(cellId);
        return mapper.toFullResponse(cell);
    }

    @Operation(summary = "Получение ячейки текущего пользователя")
    @GetMapping("/cell/user")
    public List<CellCompactResponse> getCurrentCell(){
        var cells = service.getUserCell();
        return mapper.toListCompactResponse(cells);
    }

    @Operation(summary = "Получение списка гардеробных ячеек")
    @GetMapping("/cells")
    public List<CellCompactResponse> getCells(){
        List<Cell> cells = service.getAll();
        return mapper.toListCompactResponse(cells);
    }

    @Operation(summary = "Получение отфильтрованного списка ячеек")
    @PostMapping("/cells/filter")
    public List<CellCompactResponse> getCellsFiltered(@RequestBody @Valid CellFilter filter){
        List<Cell> cells = service.getByFilter(filter);
        return mapper.toListCompactResponse(cells);
    }

    @Operation(summary = "Обновление данных ячейки")
    @PutMapping("/cell/{cellId}")
    public CellCompactResponse updateCell(@PathVariable Long cellId, @RequestBody @Valid CellUpdateRequest request){
        Cell cell = service.update(cellId, mapper.fromUpdateRequest(request));
        return mapper.toCompactResponse(cell);
    }

    @Operation(summary = "Установка пользователя в ячейку")
    @PutMapping("/cells/set")
    public CellCompactResponse setUser(@RequestParam Long userId, @RequestParam Long agrId){
        Cell cell = service.setUser(userId, agrId);
        return mapper.toCompactResponse(cell);
    }

    @Operation(summary = "Удаление пользователя из ячейки")
    @PutMapping("/cells/remove")
    public CellCompactResponse removeUser(@RequestParam Long userId){
        Cell cell = service.removeUser(userId);
        return mapper.toCompactResponse(cell);
    }

    @Operation(summary = "Удаление данных ячейки")
    @DeleteMapping("/cell/{cellId}")
    public void deleteSafe(@PathVariable Long cellId){
        service.deleteByIdSoft(cellId);
    }
}
