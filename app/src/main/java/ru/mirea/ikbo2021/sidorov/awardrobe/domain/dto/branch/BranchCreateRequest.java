package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на создание филиала")
public record BranchCreateRequest(
        @Schema(description = "Статус филиала", example = "active")
        String status,
        @Schema(description = "Название филиала", example = "Филиал №1")
        String name,
        @Schema(description = "ID ответственного менеджера", example = "1")
        Long manager_id,
        @Schema(description = "ID компании", example = "1")
        Long company_id
) {
}