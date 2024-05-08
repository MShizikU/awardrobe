package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на обновление гардеробного ряда")
public record AgrUpdateRequest(
        @Schema(description = "Статус гардеробного ряда", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        String status,

        @Schema(description = "Время открытия", example = "2024-05-05 09:00:00")
        @NotBlank(message = "Время открытия не может быть пустым")
        String open_time,

        @Schema(description = "Время закрытия", example = "2024-05-05 18:00:00")
        @NotBlank(message = "Время закрытия не может быть пустым")
        String close_time,

        @Schema(description = "ID исполнителя", example = "1")
        @NotBlank(message = "Исполнитель не может быть пустым")
        Long executor_id,

        @Schema(description = "ID головного филиала", example = "1")
        @NotBlank(message = "Филиал не может быть пустым")
        Long branch_id
) {
}
