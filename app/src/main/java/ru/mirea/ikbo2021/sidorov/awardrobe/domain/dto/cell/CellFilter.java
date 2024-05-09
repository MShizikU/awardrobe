package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Фильтрация ячеек")
public record CellFilter (
        @Schema(description = "Идентификатор ячейки", example = "1")
        Long id,

        @Schema(description = "Статус ячейки", example = "active")
        String status,

        @Schema(description = "Номер ячейки", example = "1")
        Integer sequence_number,

        @Schema(description = "ID текущего пользователя", example = "1")
        Long user_id,

        @Schema(description = "ID гардеробного ряда", example = "1")
        Long agr_id
) {
}
