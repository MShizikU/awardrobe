package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.HiddenUserResponse;

@Schema(description = "Сокращенный ответ на запрос данных компании")
public record CompanyCompactResponse(
        @Schema(description = "Идентификатор компании", example = "1")
        Long id,
        @Schema(description = "Статус компании", example = "active")
        String status,
        @Schema(description = "Название компании", example = "Рога и Копыта")
        String name,
        @Schema(description = "ИНН компании", example = "123456789012")
        String inn,
        @Schema(description = "Физический адрес компании", example = "ул. Пушкина, д. 12")
        String physical_address,
        @Schema(description = "Юридический адрес компании", example = "ул. Колотушкина, д. 1")
        String legal_address,
        @Schema(description = "Отвественный")
        HiddenUserResponse userResponse
) {
}
