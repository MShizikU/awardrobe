package ru.mirea.ikbo2021.sidorov.awardrobe.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_roles", indexes = {
        @Index(name = "idx_user_role_name", columnList = "name")
})
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_id_seq")
    @SequenceGenerator(name = "user_role_id_seq", sequenceName = "user_role_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Название
     */
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
