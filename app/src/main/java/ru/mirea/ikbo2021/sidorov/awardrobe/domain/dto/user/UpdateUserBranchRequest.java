package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(description = "Запрос на компании пользователя")
public record UpdateUserBranchRequest(
        @Schema(description = "Идентификатор пользователя", example = "1")
        @NotNull(message = "Поел должно быть задано")
        @Min(value = 1, message = "ID должен быть больше нуля")
        Long id,

        @Schema(description = "ID филилал", example = "1")
        @NotNull(message ="Поле не может быть пустым")
        @Min(value = 1, message = "ID не может быть меньше одног")
        Long branch_id
) {}
