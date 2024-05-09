package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;

@Schema(description = "Запрос на обновление ячейки")
public record CellUpdateRequest(

        @Schema(description = "Статус ячейки", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        @ValidStatus(message = "Значение статуса недопустимо")
        String status,

        @Schema(description = "ID гардеробного ряда", example = "1")
        @NotBlank(message = "Ряд не может быть пустым")
        Long agr_id
) {
}
