package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткий ответ на запрос филиала")
public record BranchCompactResponse(
        @Schema(description = "Идентификатор филиала", example = "1")
        Long id,

        @Schema(description = "Статус филиала", example = "active")
        String status,

        @Schema(description = "Название филиала", example = "Филиал №1")
        String name,

        @Schema(description = "ID ответственного", example = "1")
        Long manager_id,

        @Schema(description = "ID головной компании", example = "1")
        Long company_id
) {
}
