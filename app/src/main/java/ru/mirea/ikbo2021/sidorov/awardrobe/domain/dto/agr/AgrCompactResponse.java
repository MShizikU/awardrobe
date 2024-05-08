package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткий ответ на запрос гардеробного ряда")
public record AgrCompactResponse(
        @Schema(description = "Идентификатор гардеробного ряда", example = "1")
        Long id,

        @Schema(description = "Статус гардеробного ряда", example = "active")
        String status,

        @Schema(description = "Время открытия", example = "2024-05-05 09:00:00")
        String open_time,

        @Schema(description = "Время закрытия", example = "2024-05-05 18:00:00")
        String close_time,

        @Schema(description = "ID исполнителя", example = "1")
        Long executor_id,

        @Schema(description = "ID филиала", example = "1")
        Long branch_id
) {
}