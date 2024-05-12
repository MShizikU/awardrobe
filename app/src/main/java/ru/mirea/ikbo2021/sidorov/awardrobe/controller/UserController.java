package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.*;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;
import ru.mirea.ikbo2021.sidorov.awardrobe.mapper.UserMapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Работа с пользователями")
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    /**
     * Получение пользователя по id
     */
    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{userId}")
    public UserResponse getById(@PathVariable Long userId) {
        User user = service.getByIdStrict(userId);
        return mapper.toResponse(user);
    }


    @Operation(summary = "Получение данного пользователя")
    @GetMapping("/current")
    public UserResponse getCurrent() {
        User user = service.getCurrentUser();
        return mapper.toResponse(user);
    }

    /**
     * Пагинация пользователей по фильтру
     */
    @Operation(summary = "Получение пользователей по фильтру")
    @PostMapping("/filter")
    public List<UserResponse> findUsersByFilter(@RequestBody @Valid UserFilter filter) {

        var users = service.getByFilter(filter);
        return mapper.toResponses(users);
    }

    /**
     * Обновление пользователя
     */
    @Operation(summary = "Изменение роли пользователя")
    @PutMapping("/{userId}")
    public void changeUser(@PathVariable Long userId, @RequestBody @Valid UpdateUserRequest request) {
        service.changeUser(userId, request);
    }

    /**
     * Изменение роли пользователя
     */
    @Operation(summary = "Изменение роли пользователя")
    @PutMapping("/change-role")
    public void changeRole(@RequestBody @Valid UpdateUserRoleRequest request) {
        service.changeRole(request);
    }

    /**
     * Изменение компании пользователя
     */
    @Operation(summary = "Изменение компании пользователя")
    @PutMapping("/change-company")
    public void changeCompany(@RequestBody @Valid UpdateUserCompanyRequest request){
        service.changeCompany(request);
    }

    /**
     * Смена пароля пользователя
     */
    @Operation(summary = "Смена пароля пользователя")
    @PostMapping("/change-password")
    public void changePassword(@RequestBody @Valid UpdateUserPasswordRequest request) {
        service.changePassword(request);
    }
}
