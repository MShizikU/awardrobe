package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Запрос на компании пользователя")
public record UpdateUserCompanyRequest(
        @Schema(description = "ID компании", example = "1")
        Long company_id
) {}
