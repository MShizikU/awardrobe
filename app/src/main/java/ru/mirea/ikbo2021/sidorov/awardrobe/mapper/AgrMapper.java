package ru.mirea.ikbo2021.sidorov.awardrobe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        uses = {CellMapper.class, UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring"
)
public interface AgrMapper {

    @Mappings({
        @Mapping(target = "executor_id", source = "executor.id"),
        @Mapping(target = "branch_id", source = "branch.id"),
    })
    AgrCompactResponse toCompactResponse(Agr agr);

    @Mappings({
            @Mapping(target = "branch.manager_id", source = "agr.branch.manager.id"),
            @Mapping(target = "branch.company_id", source = "agr.branch.company.id")
    })
    AgrFullResponse toFullResponse(Agr agr, List<Cell> cells);

    @Mappings({
            @Mapping(target = "executor.id", source = "executor_id"),
            @Mapping(target = "branch.id", source = "branch_id")
    })
    Agr fromCreateRequest(AgrCreateRequest request);

    @Mappings({
            @Mapping(target = "executor.id", source = "executor_id"),
            @Mapping(target = "branch.id", source = "branch_id")
    })
    Agr fromUpdateRequest(AgrUpdateRequest request);

    List<AgrCompactResponse> toListCompactResponse(List<Agr> agrs);
}
