package ru.mirea.ikbo2021.sidorov.awardrobe.mapper;

import org.mapstruct.Mapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.UserResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserResponse toResponse(User user);
    List<UserResponse> toResponses(List<User> users);
}
