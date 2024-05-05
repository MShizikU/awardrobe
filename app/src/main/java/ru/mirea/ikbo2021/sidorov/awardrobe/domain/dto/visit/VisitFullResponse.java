package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.HiddenUserResponse;

@Schema(description = "Полный ответ на запрос визита")
public record VisitFullResponse(
        @Schema(description = "Идентификатор визита", example = "1")
        Long id,

        @Schema(description = "Время начала визита", example = "2024-05-05 10:00:00")
        String startTime,

        @Schema(description = "Время окончания визита", example = "2024-05-05 12:00:00")
        String endTime,

        @Schema(description = "Посетитель")
        HiddenUserResponse user,

        @Schema(description = "Использованная ячейка")
        CellCompactResponse cell
) {
}
