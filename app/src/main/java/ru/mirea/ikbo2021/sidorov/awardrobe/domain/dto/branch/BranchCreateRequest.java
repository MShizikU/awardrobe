package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;

@Schema(description = "Запрос на создание филиала")
public record BranchCreateRequest(
        @Schema(description = "Статус филиала", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        @ValidStatus(message = "Значение статуса недопустимо")
        @NotNull(message = "Поле является обязательным")
        String status,

        @Schema(description = "Название филиала", example = "Филиал №1")
        @NotBlank(message = "Название не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        String name,

        @Schema(description = "ID ответственного менеджера", example = "1")
        @Min(value = 1, message = "Ответсвенный не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        Long manager_id,

        @Schema(description = "ID компании", example = "1")
        @Min(value = 1, message = "Компания не может отсутсвовать")
        @NotNull(message = "Поле является обязательным")
        Long company_id
) {
}