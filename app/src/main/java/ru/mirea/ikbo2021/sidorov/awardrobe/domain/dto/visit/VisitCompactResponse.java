package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткий ответ на запрос визита")
public record VisitCompactResponse(

        @Schema(description = "Идентификатор визита", example = "1")
        Long id,

        @Schema(description = "Время начала визита", example = "2024-05-05 10:00:00")
        String start_time,

        @Schema(description = "Время окончания визита", example = "2024-05-05 12:00:00")
        String end_time,

        @Schema(description = "ID ячейки", example = "1")
        Long cell_id,

        @Schema(description = "ID пользователя", example = "1")
        Long user_id
) {
}
