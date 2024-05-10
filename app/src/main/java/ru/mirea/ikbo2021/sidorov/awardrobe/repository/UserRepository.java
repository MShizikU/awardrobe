package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:username IS null OR u.username LIKE %:username%) " +
            "AND (:email IS null OR u.email LIKE %:email%) " +
            "AND (:roleID IS null OR u.role.id = :roleId) " +
            "AND (:isDisposable IS null AND u.isDisposable = :isDisposable) " +
            "AND (:status IS null OR u.status LIKE %:status%) " +
            "AND (:id IS null OR u.id = :id)")
    List<User> findByFilter(Long id, Long roleId, String username, String email, String status, Boolean isDisposable);
}