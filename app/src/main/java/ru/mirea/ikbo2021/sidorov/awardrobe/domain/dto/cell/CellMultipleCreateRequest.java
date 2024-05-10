package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Запрос на создание множества ячеек")
public record CellMultipleCreateRequest (
        @Schema(description = "Идентификатор гардеробного ряда", example = "1")
        @Min(value = 1, message = "ID должен быть больше или равно 1")
        @NotNull(message = "Поле является обязательным")
        Long agr_id,

        @Schema(description = "Количество ячеек")
        @Min(value = 1, message = "Количество должно быть больше или равно 1")
        @Max(value = 1000, message = "Количество не должно быть больше 1000")
        @NotNull(message = "Поле является обязательным")
        Long amount
){

}
