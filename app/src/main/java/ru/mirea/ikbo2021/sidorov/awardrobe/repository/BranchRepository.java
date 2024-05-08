package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;

import java.util.List;

@Repository
public interface BranchRepository  extends JpaRepository<Branch, Long> {
    public List<Branch> findBranchesByCompany_Id(Long companyId);

    @Query("SELECT b FROM Branch b WHERE " +
            "(:id IS null OR b.id = :id) " +
            "AND (:status IS null OR b.status LIKE %:status%) " +
            "AND (:name IS null OR b.name LIKE %:name%) " +
            "AND (:manager_id IS null OR b.manager.id = :manager_id) " +
            "AND (:company_id IS null OR b.company.id = :company_id)")
    List<Branch> findAllWithFilter(Long id, String status, String name, Long manager_id, Long company_id);
}
