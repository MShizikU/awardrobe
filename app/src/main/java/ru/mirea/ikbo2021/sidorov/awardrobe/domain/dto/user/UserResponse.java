package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.UserRole;

@Builder
@Schema(description = "Ответ на запрос пользователя")
public record UserResponse (
    @Schema(description = "Идентификатор пользователя", example = "1")
    Long id,

    @Schema(description = "Логин пользователя", example = "ivanov")
    String username,

    @Schema(description = "Почта пользователя", example = "example@tinkoff.ru")
    String email,

    @Schema(description = "Является ли пользователь одноразовым",example = "true")
    Boolean isDisposable,

    @Schema(description = "Статус пользователя", example = "active")
    String status,

    @Schema(description = "Роль пользователя", example = "USER")
    UserRole role,

    @Schema(description = "Филиал", example = "1")
    Long branch_id

){
}
