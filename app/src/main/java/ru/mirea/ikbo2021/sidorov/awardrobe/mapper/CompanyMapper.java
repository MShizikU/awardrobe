package ru.mirea.ikbo2021.sidorov.awardrobe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyCreationRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyFullResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyUpdateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;

import java.util.List;

@Mapper(
        uses = {UserMapper.class, BranchMapper.class},
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring"
)
public interface CompanyMapper {
    @Mappings({
            @Mapping(target ="legal_address", source ="legalAddress"),
            @Mapping(target = "legalAddress", source = "legal_address"),
            @Mapping(target = "physical_address", source = "physicalAddress"),
            @Mapping(target = "physicalAddress", source = "physical_address"),
            @Mapping(target = "manager_id", source = "manager.id"),
            @Mapping(target = "manager.id", source = "manager_id")
    })
    CompanyCompactResponse toCompactResponse(Company company);
    CompanyFullResponse toFullResponse(Company company, List<Branch> branches);
    Company fromCreateRequest(CompanyCreationRequest request);
    Company fromUpdateRequest(CompanyUpdateRequest request);
}
