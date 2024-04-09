package ru.mirea.ikbo2021.sidorov.awardrobe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", indexes = {
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_email", columnList = "email")
})
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Уникальный код пользователя
     */
    @Column(name = "hashcode", unique = false, nullable = false)
    private String hashcode;

    /**
     * Является ли пользователь удаляемым
     */
    @Column(name = "is_disposable", nullable = false)
    private Boolean isDisposable;

    /**
     * Статус
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * Имя пользователя
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * Электронная почта
     */
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Номер телефона
     */
    @Column(name = "phone", unique = true)
    private String phone;

    /**
     * Пароль
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Роль
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "author_id")
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
