package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.UserRole;

public record UserFilter(
        @Schema(description = "Идентификатор пользователя", example = "1")
        Long id,

        @Schema(description = "Логин пользователя", example = "ivanov")
        String username,

        @Schema(description = "Почта пользователя", example = "example@tinkoff.ru")
        String email,

        @Schema(description = "Статус пользователя", example = "active")
        String status,

        @Schema(description = "Роль пользователя", example = "USER")
        Long role_id
) {
}
