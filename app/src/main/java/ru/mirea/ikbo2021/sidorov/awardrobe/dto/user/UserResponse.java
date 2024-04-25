package ru.mirea.ikbo2021.sidorov.awardrobe.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import ru.mirea.ikbo2021.sidorov.awardrobe.model.UserRole;

@Builder
@Schema(description = "Ответ на запрос пользователя")
public record UserResponse (
    @Schema(description = "Идентификатор пользователя", example = "1")
    Long id,

    @Schema(description = "Логин пользователя", example = "ivanov")
    String username,

    @Schema(description = "Почта пользователя", example = "example@tinkoff.ru")
    String email,

    @Schema(description = "Уникальный код пользователя", example = "1asdf1142124xfca")
    String hashcode,

    @Schema(description = "Является ли пользователь одноразовым",example = "true")
    Boolean isDisposable,

    @Schema(description = "Статус пользователя", example = "active")
    String status,

    @Schema(description = "Телефонный номер пользователя", example = "sidorov@example.com")
    String phone,

    @Schema(description = "Роль пользователя", example = "USER")
    UserRole role

){
}
