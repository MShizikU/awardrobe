package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;

import java.util.List;

@Repository
public interface AgrRepository extends JpaRepository<Agr, Long> {
    public List<Agr> findAgrsByBranch_Id(Long id);

    @Query("SELECT a FROM Agr a WHERE " +
            "(:id IS null OR a.id = :id) " +
            "AND (:status IS null OR :status = '' OR a.status = :status) " +
            "AND (:openTime IS null OR a.open_time LIKE %:openTime%)" +
            "AND (:closeTime IS null OR a.close_time LIKE %:closeTime%)" +
            "AND (:executor_id IS null OR a.executor.id = :executor_id) " +
            "AND (:branch_id IS null OR a.branch.id = :branch_id)"
    )
    List<Agr> findAllWithFilter(Long id, String status, String openTime, String closeTime, Long executor_id, Long branch_id);
}
