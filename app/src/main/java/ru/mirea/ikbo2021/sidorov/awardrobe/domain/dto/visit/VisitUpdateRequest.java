package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Schema(description = "Запрос на обновление визита")
public record VisitUpdateRequest(
        @Schema(description = "Время начала визита", example = "2024-05-05 10:00:00")
        @NotBlank(message = "Время начала не может быть пустым")
        Timestamp start_time,

        @Schema(description = "Время окончания визита", example = "2024-05-05 12:00:00")
        Timestamp end_time,

        @Schema(description = "ID ячейки", example = "1")
        @NotBlank(message = "ID ячейки не может быть пустым")
        Long cell_id,

        @Schema(description = "ID пользователя", example = "1")
        @NotBlank(message = "ID пользователя не может быть пустым")
        Long user_id
) {
}
