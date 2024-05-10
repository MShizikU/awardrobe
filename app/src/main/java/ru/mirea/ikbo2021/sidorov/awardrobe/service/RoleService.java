package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.UserRole;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.UserRoleRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final UserRoleRepository repository;

    public UserRole getByIdStrict(Long roleId){
        var role = repository.findById(roleId);
        if (role.isEmpty()) throw new EntityNotFound("user_role", "id", roleId.toString());
        return role.get();
    }

    public UserRole getByRoleName(String name){
        var role = repository.findByName(name);
        if (role.isEmpty()) throw new EntityNotFound("user_role", "name", name);
        return role.get();
    }
}
