package ru.mirea.ikbo2021.sidorov.awardrobe.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branches")
public class Branch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Статус
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * Название
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Ответсвенный
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "manager_id")
    private User manager;

    /**
     * Головная компания
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "company_id")
    private Company company;
}