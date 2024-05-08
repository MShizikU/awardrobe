package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByInn(String inn);

    List<Company> findCompaniesByStatus(String status);

    @Query("SELECT c FROM Company c WHERE " +
            "(:id IS null OR c.id = :id) " +
            "AND (:status IS null OR c.status LIKE %:status%) " +
            "AND (:inn IS null OR c.inn LIKE %:inn%) " +
            "AND (:physicalAddress IS null OR c.physicalAddress LIKE %:status%) " +
            "AND (:legalAddress IS null OR c.legalAddress LIKE %:legalAddress%)" +
            "AND (:manager_id IS null OR c.manager.id = :manager_id)")
    List<Company> findAllWithFilter(Long id, String status, String inn, String physicalAddress, String legalAddress, Long manager_id);
}
