package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на обновление пароля пользователя")
public record UpdateUserPasswordRequest(

        @Schema(description = "Идентификатор пользователя", example = "1")
        @NotNull(message = "Поел должно быть задано")
        @Min(value = 1, message = "ID должен быть больше нуля")
        Long id,

        @Schema(description = "Старый пароль", example = "my_1secret1_password")
        @Size(max = 255, message = "Длина пароля должна быть не больше 255 символов")
        String oldPassword,

        @Schema(description = "Новый пароль", example = "my_2secret2_password")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$", message = "Пароль должен содержать как минимум одну букву и одну цифру, и быть длиной не менее 8 символов")
        @Size(max = 255, message = "Длина пароля должна быть не больше 255 символов")
        String newPassword
) {
}
