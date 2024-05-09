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
        unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring"
)
public interface CompanyMapper {

    @Mappings({
            @Mapping(target ="legal_address", source ="legalAddress"),
            @Mapping(target = "physical_address", source = "physicalAddress")
    })
    CompanyCompactResponse toCompactResponse(Company company);

    @Mappings({
            @Mapping(target ="legal_address", source ="company.legalAddress"),
            @Mapping(target = "physical_address", source = "company.physicalAddress")
    })
    CompanyFullResponse toFullResponse(Company company, List<Branch> branches);

    List<CompanyCompactResponse> toListCompactResponse(List<Company> compacts);


    @Mappings({
            @Mapping(target = "legalAddress", source = "legal_address"),
            @Mapping(target = "physicalAddress", source = "physical_address"),
            @Mapping(target = "manager.id", source = "manager_id")
    })
    Company fromCreateRequest(CompanyCreationRequest request);

    @Mappings({
            @Mapping(target = "legalAddress", source = "legal_address"),
            @Mapping(target = "physicalAddress", source = "physical_address"),
            @Mapping(target = "manager.id", source = "manager_id")
    })
    Company fromUpdateRequest(CompanyUpdateRequest request);
}
