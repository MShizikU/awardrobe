package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;

@Schema(description = "Запрос на создание компании")
public record CompanyCreationRequest(
        @Schema(description = "Статус компании", example = "active")
        @NotBlank(message = "Статус не может быть пустым")
        @ValidStatus(message = "Значение статуса недопустимо")
        @NotNull(message = "Поле является обязательным")
        String status,

        @Schema(description = "Название компании", example = "Рога и Копыта")
        @NotBlank(message = "Название не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        String name,

        @Schema(description = "ИНН компании", example = "123456789012")
        @NotBlank(message = "ИНН не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        String inn,

        @Schema(description = "Физический адрес компании", example = "ул. Пушкина, д. 12")
        @NotBlank(message = "Физический адрес не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        String physical_address,

        @Schema(description = "Юридический адрес компании", example = "ул. Колотушкина, д. 1")
        @NotBlank(message = "Юридический адрес не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        String legal_address,

        @Schema(description = "ID ответсвенного", example = "1")
        @Min(value = 1 , message = "Ответсвенный не может быть пустым")
        @NotNull(message = "Поле является обязательным")
        Long manager_id
) {
}
