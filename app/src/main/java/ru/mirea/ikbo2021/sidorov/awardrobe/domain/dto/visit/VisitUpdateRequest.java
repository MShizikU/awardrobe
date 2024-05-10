package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Schema(description = "Запрос на обновление визита")
public record VisitUpdateRequest(
        @Schema(description = "Время начала визита", example = "2024-05-05 10:00:00")
        @NotBlank(message = "Время начала не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        Timestamp start_time,

        @Schema(description = "Время окончания визита", example = "2024-05-05 12:00:00")
        Timestamp end_time,

        @Schema(description = "ID ячейки", example = "1")
        @Min(value = 1, message = "ID ячейки не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        Long cell_id,

        @Schema(description = "ID пользователя", example = "1")
        @Min(value = 1, message = "ID пользователя не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        Long user_id
) {
}
