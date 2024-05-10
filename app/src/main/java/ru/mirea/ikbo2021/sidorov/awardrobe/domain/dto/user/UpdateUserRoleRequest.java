package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Запрос на обновление роли пользователя")
public record UpdateUserRoleRequest(

        @Schema(description = "Идентификатор пользователя", example = "1")
        @NotNull(message = "Поел должно быть задано")
        @Min(value = 1, message = "ID должен быть больше нуля")
        Long id,

        @Schema(description = "Роль пользователя", example = "ROLE_USER")
        @NotBlank(message = "Роль является обязательным параметром")
        String role
) {
}