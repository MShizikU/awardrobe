package ru.mirea.ikbo2021.sidorov.awardrobe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellCreateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellFullResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellUpdateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;

import java.util.List;

@Mapper(
        uses = { UserMapper.class, AgrMapper.class },
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring"
)
public interface CellMapper {

    @Mappings({
            @Mapping(target = "sequence_number", source = "sequenceNumber"),
            @Mapping(target = "user_id", source = "user.id"),
            @Mapping(target = "agr_id", source = "agr.id"),
            @Mapping(target = "user.id", source = "user_id"),
            @Mapping(target = "agr.id", source = "agr_id"),
    })

    CellCompactResponse toCompactResponse(Cell cell);

    CellFullResponse toFullResponse(Cell cell);

    Cell toCreateRequest(CellCreateRequest request);

    Cell toUpdateRequest(CellUpdateRequest request);

    List<CellCompactResponse> toListCompactResponse(List<Cell> cells);
}
