package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;

@Schema(description = "Запрос на изменение данных компании")
public record CompanyUpdateRequest(
        @Schema(description = "Статус компании", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        @ValidStatus(message = "Значение статуса недопустимо")
        String status,

        @Schema(description = "Название компании", example = "Рога и Копыта")
        @NotBlank(message = "Название не может быть пустым")
        String name,

        @Schema(description = "ИНН компании", example = "123456789012")
        @NotBlank(message = "ИНН не может быть пустым")
        String inn,

        @Schema(description = "Физический адрес компании", example = "ул. Пушкина, д. 12")
        @NotBlank(message = "Физический адрес не может быть пустым")
        String physical_address,

        @Schema(description = "Юридический адрес компании", example = "ул. Колотушкина, д. 1")
        @NotBlank(message = "Юридический адрес не может быть пустым")
        String legal_address,

        @Schema(description = "ID ответсвенного", example = "1")
        @NotBlank(message = "Ответсвенный не может быть пустым")
        Long manager_id
) {
}
