package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.model.UserRole;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
   Optional<UserRole> findByName (String name);
}
