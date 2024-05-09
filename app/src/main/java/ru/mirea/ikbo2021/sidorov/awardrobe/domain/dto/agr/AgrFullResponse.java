package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch.BranchCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.HiddenUserResponse;

import java.util.List;

@Schema(description = "Полный ответ на запрос гардеробного ряда")
public record AgrFullResponse(
        @Schema(description = "Идентификатор гардеробного ряда", example = "1")
        Long id,

        @Schema(description = "Статус гардеробного ряда", example = "active")
        String status,

        @Schema(description = "Время открытия", example = "09:00")
        String open_time,

        @Schema(description = "Время закрытия", example = "18:00")
        String close_time,

        @Schema(description = "Текущий исполнитель")
        HiddenUserResponse executor,

        @Schema(description = "Головной филиал")
        BranchCompactResponse branch,

        @Schema(description = "Список ячеек")
        List<CellCompactResponse> cells
) {
}
