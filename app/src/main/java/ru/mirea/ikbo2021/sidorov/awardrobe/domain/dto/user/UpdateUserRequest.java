package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.mirea.ikbo2021.sidorov.awardrobe.validation.validator.ValidStatus;

public record UpdateUserRequest(

        @Schema(description = "Почта пользователя", example = "example@tinkoff.ru")
        @NotNull(message = "Значение не может быть пустое")
        @NotBlank(message = "Значение не может быть пустое")
        String email,

        @Schema(description = "Статус пользователя", example = "active")
        @NotNull(message = "Значение не может быть пустое")
        @NotBlank(message = "Значение не может быть пустое")
        @ValidStatus(message = "Значение статуса недопустимо")
        String status,

        @Schema(description = "Роль пользователя", example = "1")
        @NotNull(message = "Значение не может быть пустое")
        @Min(value = 1, message = "ID должен быть больше 1")
        Long role_id,

        @Schema(description = "Филиал пользователя", example = "1")
        @NotNull(message = "Значение не может быть пустое")
        @Min(value = 1, message = "ID должен быть больше 1")
        Long branch_id
) {
}
