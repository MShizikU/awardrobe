package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyCreationRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyFullResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyUpdateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;
import ru.mirea.ikbo2021.sidorov.awardrobe.mapper.CompanyMapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.BranchService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Работа с компаниями")
public class CompanyController {
    private final CompanyService service;
    private final BranchService branchService;
    private final CompanyMapper mapper;

    @Operation(summary = "Создание компании")
    @PostMapping("/company")
    public CompanyCompactResponse createCompany(@RequestBody @Valid CompanyCreationRequest request){
        Company company = service.create(mapper.fromCreateRequest(request));
        return mapper.toCompactResponse(company);
    }

    @Operation(summary = "Получение полных данных компании")
    @GetMapping("/company/{companyId}")
    public CompanyFullResponse getFullCompany(@PathVariable Long companyId){
        Company company = service.getById(companyId);
        return mapper.toFullResponse(company, branchService.getBranchesByCompany(company.getId()));
    }

    @Operation(summary = "Получение списка всех компаний")
    @GetMapping("/companies")
    public List<CompanyCompactResponse> getAllCompanies(){
        List<Company> companies = service.getAll();
        return mapper.toListCompactResponse(companies);
    }

    @Operation(summary = "Получение списка компаний по статусу")
    @GetMapping("/companies/{status}")
    public List<CompanyCompactResponse> getCompanies(@PathVariable String status){
        List<Company> companies = service.getByStatus(status);
        return mapper.toListCompactResponse(companies);
    }

    @Operation(summary = "Обновление данных компании")
    @PutMapping("/{companyId}")
    public CompanyCompactResponse updateCompany(@PathVariable Long companyId, @RequestBody @Valid CompanyUpdateRequest request){
        Company company = service.update(companyId, mapper.fromUpdateRequest(request));
        return mapper.toCompactResponse(company);
    }

    @Operation(summary = "Удаление данных компании")
    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable Long companyId){
        service.deleteById(companyId);
    }
}
