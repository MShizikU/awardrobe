package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.*;
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

    @Operation(summary = "Получение пользователей по фильтру")
    @PostMapping("/companies/filter")
    public List<CompanyCompactResponse> findPostsByFilter(@RequestBody @Valid CompanyFilter filter) {
        var companies = service.findByFilter(filter);
        return mapper.toListCompactResponse(companies);
    }

    @Operation(summary = "Получение списка всех компаний")
    @GetMapping("/companies")
    public List<CompanyCompactResponse> getAllCompanies(){
        List<Company> companies = service.getAll();
        return mapper.toListCompactResponse(companies);
    }

    @Operation(summary = "Обновление данных компании")
    @PutMapping("/company/{companyId}")
    public CompanyCompactResponse updateCompany(@PathVariable Long companyId, @RequestBody @Valid CompanyUpdateRequest request){
        Company company = service.update(companyId, mapper.fromUpdateRequest(request));
        return mapper.toCompactResponse(company);
    }

    @Operation(summary = "Удаление данных компании")
    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable Long companyId){
        service.deleteByIdSoft(companyId);
    }
}
