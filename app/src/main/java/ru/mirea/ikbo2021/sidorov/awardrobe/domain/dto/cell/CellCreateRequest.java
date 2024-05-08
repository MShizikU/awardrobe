package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на создание ячейки")
public record CellCreateRequest(

        @Schema(description = "Статус ячейки", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        String status,

        @Schema(description = "ID текущего пользователя", example = "1")
        Long user_id,

        @Schema(description = "ID гардеробного ряда", example = "1")
        @NotBlank(message = "Ряд не может быть пустым")
        Long agr_id
) {
}
