package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr.AgrCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.HiddenUserResponse;

@Schema(description = "Полный ответ на запрос ячейки")
public record CellFullResponse(
        @Schema(description = "Идентификатор ячейки", example = "1")
        Long id,

        @Schema(description = "Статус ячейки", example = "active")
        String status,

        @Schema(description = "Номер ячейки", example = "1")
        Integer sequence_number,

        @Schema(description = "Текущий пользователь")
        HiddenUserResponse user,

        @Schema(description = "Гардеробный ряд")
        AgrCompactResponse agr
) {
}
