package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на изменение данных компании")
public record CompanyUpdateRequest(
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
        @Schema(description = "ID ответсвенного", example = "1")
        Long manager_id
) {
}
