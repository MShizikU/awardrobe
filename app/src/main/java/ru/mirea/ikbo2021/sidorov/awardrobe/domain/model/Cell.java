package ru.mirea.ikbo2021.sidorov.awardrobe.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cell")
public class Cell {

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
     * Порядковый номер
     */
    @Column(name = "sequence_number", nullable = false)
    private Integer sequence_number;

    /**
     * Родительский автоматизированный гардеробный ряд
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "agr_id")
    private Agr agr;

    /**
     * Текущий пользователь
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
