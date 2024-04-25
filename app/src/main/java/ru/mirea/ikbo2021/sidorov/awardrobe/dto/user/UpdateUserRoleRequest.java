package ru.mirea.ikbo2021.sidorov.awardrobe.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на обновление роли пользователя")
public record UpdateUserRoleRequest(

        @Schema(description = "Идентификатор пользователя", example = "1")
        Long id,

        @Schema(description = "Роль пользователя", example = "ROLE_USER")
        @NotBlank(message = "Роль является обязательным параметром")
        String role
) {
}