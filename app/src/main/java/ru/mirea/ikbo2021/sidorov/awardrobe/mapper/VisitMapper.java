package ru.mirea.ikbo2021.sidorov.awardrobe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitCreateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitFullResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitUpdateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Visit;

import java.util.List;

@Mapper(
        uses = {UserMapper.class, BranchMapper.class, CellMapper.class},
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring"
)
public interface VisitMapper {
    @Mappings({
            @Mapping(target = "start_time", source = "startTime"),
            @Mapping(target = "end_time", source = "endTime"),
            @Mapping(target = "startTime", source = "start_time"),
            @Mapping(target = "endTime", source = "end_time"),
            @Mapping(target = "user_id", source = "user.id"),
            @Mapping(target = "user.id", source = "user_id"),
            @Mapping(target = "cell.id", source = "cell_id"),
            @Mapping(target = "cell_id", source = "cell.id")
    })
    VisitCompactResponse toCompatResponse(Visit visit);
    VisitFullResponse toFullResponse(Visit visit);
    Visit fromCreateRequest(VisitCreateRequest request);
    Visit fromUpdateRequest(VisitUpdateRequest request);
    List<Visit> toListCompactResponse(List<Visit> visits);
}
