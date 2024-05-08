package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record HiddenUserResponse(
        @Schema (description = "Уникальный идентификатор пользваотеля", example = "1")
        Long id,
        @Schema (description = "Имя пользователя", example = "shiz")
        String username
) {
}
