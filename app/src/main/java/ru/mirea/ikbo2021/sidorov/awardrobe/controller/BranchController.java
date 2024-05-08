package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyUpdateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;
import ru.mirea.ikbo2021.sidorov.awardrobe.mapper.BranchMapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.AgrService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.BranchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BranchController {
    private final BranchService service;
    private final AgrService agrService;
    private final BranchMapper mapper;

    @Operation(summary = "Создание филиала")
    @PostMapping("/branch")
    public BranchCompactResponse createBranch(@RequestBody @Valid BranchCreateRequest request) {
        Branch branch = service.create(mapper.fromCreateRequest(request));
        return mapper.toCompactResponse(branch);
    }

    @Operation(summary = "Получение полных данных филиала")
    @GetMapping("/branch/{branchId}")
    public BranchFullResponse getFullBranch(@PathVariable Long branchId){
        Branch branch = service.getById(branchId);
        return mapper.toFullResponse(branch, agrService.getAgrsByBranchId(branch.getId()));
    }

    @Operation(summary = "Получение списка филиалов")
    @GetMapping("/branches")
    public List<BranchCompactResponse> getAll(){
        List<Branch> branches = service.getAll();
        return mapper.toListCompactResponse(branches);
    }

    @Operation(summary = "Получение фильтрованнаго списка филиалов")
    @GetMapping("/branches/filter")
    public List<BranchCompactResponse> getAllByFilter(@RequestBody @Valid BranchFilter filter){
        var branches = service.getByFilter(filter);
        return mapper.toListCompactResponse(branches);
    }

    @Operation(summary = "Обновление данных филиала")
    @PutMapping("/branch/{branchId}")
    public BranchCompactResponse updateBranch(@PathVariable Long branchId, @RequestBody @Valid BranchUpdateRequest request){
        Branch branch = service.update(branchId, mapper.fromUpdateRequest(request));
        return mapper.toCompactResponse(branch);
    }

    @Operation(summary = "Удаление данных компании")
    @DeleteMapping("/branch/{branchId}")
    public void delete(@PathVariable Long branchId){
        service.deleteByIdSoft(branchId);
    }
}
