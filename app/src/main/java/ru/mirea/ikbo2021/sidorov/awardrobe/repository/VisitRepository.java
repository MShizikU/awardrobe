package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Visit;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v FROM Visit v WHERE " +
            "(:id is NULL OR v.id = :id) " +
            "AND (:cell_id IS null OR v.cell.id = :cell_id) " +
            "AND (:user_id IS null OR v.user.id = :user_id)"
    )
    public List<Visit> findByFilter(Long id, Long cell_id, Long user_id);

    @Query("SELECT v FROM Visit v WHERE v.user.id = :user_id AND v.end_time = :endTime")
    public Optional<Visit> findByUserIdAndEndTime(Long user_id, Timestamp endTime);
}
