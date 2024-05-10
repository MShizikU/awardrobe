package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
@Schema(description = "Запрос на обновление почты пользователя")
public record UpdateUserEmailRequest(

        @Schema(description = "Идентификатор пользователя", example = "1")
        @NotNull(message = "Поел должно быть задано")
        @Min(value = 1, message = "ID должен быть больше нуля")
        Long id,

        @Schema(description = "Адрес электронной почты", example = "jondoe@gmail.com")
        @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
        @NotBlank(message = "Адрес электронной почты не может быть пустыми")
        @Email(message = "Email адрес должен быть в формате user@example.com")
        String email
) {
}