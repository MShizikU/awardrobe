package ru.mirea.ikbo2021.sidorov.awardrobe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch.BranchCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch.BranchCreateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch.BranchFullResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch.BranchUpdateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;

import java.util.List;

@Mapper(
        uses = {AgrMapper.class, UserMapper.class, CompanyMapper.class},
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring"
)
public interface BranchMapper {
    @Mappings({
            @Mapping(target = "manager_id", source = "manager.id"),
            @Mapping(target = "company_id", source = "company.id"),
            @Mapping(target = "manager.id", source = "manager_id"),
            @Mapping(target = "company.id", source = "company_id")
    })

    BranchCompactResponse toCompactResponse(Branch branch);
    BranchFullResponse toFullResponse(Branch branch, List<Agr> agrs);
    Branch fromCreateRequest(BranchCreateRequest request);
    Branch fromUpdateRequest(BranchUpdateRequest request);
    List<BranchCompactResponse> toListCompactResponse(List<Branch> branches);
}
