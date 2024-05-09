package ru.mirea.ikbo2021.sidorov.awardrobe.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies", indexes = {
        @Index(name = "idx_inn", columnList = "inn")
})
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Статус
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * Название
     */
    @Column(name="name", nullable = false)
    private String name;

    /**
     * ИНН
     */
    @Column(name = "inn", unique = true, nullable = false)
    private String inn;

    /**
     * Физический адрес
     */
    @Column(name = "physical_address", nullable = false)
    private String physicalAddress;

    /**
     * Юридический адрес
     */
    @Column(name = "legal_address", nullable = false)
    private String legalAddress;

    /**
     * Ответсвенный
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "manager_id")
    private User manager;


    /**
     * Проверка является ли компания активной
     *
     * @return true, если активная
     */
    public boolean isActive()
    {
        return Objects.equals(status, "active");
    }
}
