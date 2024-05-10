package ru.mirea.ikbo2021.sidorov.awardrobe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.user.UserResponse;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring"
)
public interface UserMapper {

    UserResponse toResponse(User user);
    List<UserResponse> toResponses(List<User> users);
}
