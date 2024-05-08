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
@Table(name = "agrs")
public class Agr {

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
     * Время начала работы
     */
    @Column(name = "open_time", nullable = false)
    private String openTime;

    /**
     * Время окончания работы
     */
    @Column(name = "close_time", nullable = false)
    private String closeTime;

    /**
     * Текущий исполнитель
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "executor_id")
    private User executor;

    /**
     * Головной филиал
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "branch_id")
    private Branch branch;
}