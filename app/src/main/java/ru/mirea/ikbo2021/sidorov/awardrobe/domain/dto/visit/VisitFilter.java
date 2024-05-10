package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit;

import io.swagger.v3.oas.annotations.media.Schema;

public record VisitFilter(
        @Schema(description = "Идентификатор визита", example = "1")
        Long id,

        @Schema(description = "ID ячейки", example = "1")
        Long cell_id,

        @Schema(description = "ID пользователя", example = "1")
        Long user_id
) {
}
