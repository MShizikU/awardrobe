package ru.mirea.ikbo2021.sidorov.awardrobe.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Builder
@Schema(description = "Запрос на обновление почты пользователя")
public record UpdateUserEmailRequest(

        @Schema(description = "Адрес электронной почты", example = "jondoe@gmail.com")
        @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
        @NotBlank(message = "Адрес электронной почты не может быть пустыми")
        @Email(message = "Email адрес должен быть в формате user@example.com")
        String email
) {
}