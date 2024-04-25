package ru.mirea.ikbo2021.sidorov.awardrobe.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на удаление пользователя")
public class DeleteUserRequest {

    @Schema(description = "Идентификатор пользователя", example = "1")
    long userId;
}
