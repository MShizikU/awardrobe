package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;

@Schema(description = "Запрос на обновление ячейки")
public record CellUpdateRequest(

        @Schema(description = "Статус ячейки", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        @ValidStatus(message = "Значение статуса недопустимо")
        @NotNull(message = "Поле является обязательным")
        String status,

        @Schema(description = "ID гардеробного ряда", example = "1")
        @Min(value = 1, message = "Ряд не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        Long agr_id
) {
}
