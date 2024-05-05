package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Запрос обновления филиала")
public record BranchUpdateRequest(
        @Schema(description = "Статус филиала", example = "active")
        String status,

        @Schema(description = "Название филиала", example = "Филиал №1")
        String name,

        @Schema(description = "Код филиала", example = "123456")
        String branchCode,

        @Schema(description = "ID ответственного менеджера", example = "1")
        Long managerId,

        @Schema(description = "ID компании", example = "1")
        Long companyId
) {

}