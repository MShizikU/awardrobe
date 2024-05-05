package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr.AgrCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyCompactResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.HiddenUserResponse;

import java.util.List;

@Schema(description = "Полный ответ на запрос филиала")
public record BranchFullResponse(
        @Schema(description = "Идентификатор филиала", example = "1")
        Long id,

        @Schema(description = "Статус филиала", example = "active")
        String status,

        @Schema(description = "Название филиала", example = "Филиал №1")
        String name,

        @Schema(description = "Код филиала", example = "123456")
        String branchCode,

        @Schema(description = "Ответственный")
        HiddenUserResponse manager,

        @Schema(description = "Головная компания")
        CompanyCompactResponse company,

        @Schema(description = "Список гардеробных рядов")
        List<AgrCompactResponse> agrs
) {
}
