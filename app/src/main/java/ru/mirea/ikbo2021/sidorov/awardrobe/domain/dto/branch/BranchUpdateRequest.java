package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;


@Schema(description = "Запрос обновления филиала")
public record BranchUpdateRequest(
        @Schema(description = "Статус филиала", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        @ValidStatus(message = "Значение статуса недопустимо")
        String status,

        @Schema(description = "Название филиала", example = "Филиал №1")
        @NotBlank(message = "Название не может быть пустым")
        String name,

        @Schema(description = "ID ответственного менеджера", example = "1")
        @NotBlank(message = "Ответсвенный не может быть пустым")
        Long manager_id,

        @Schema(description = "ID компании", example = "1")
        @NotBlank(message = "Компания не может отсутсвовать")
        Long company_id
) {

}