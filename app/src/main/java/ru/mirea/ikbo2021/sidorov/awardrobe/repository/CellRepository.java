package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;

import java.util.List;
import java.util.Optional;

@Repository
public interface CellRepository  extends JpaRepository<Cell, Long> {
    public List<Cell> findCellsByAgr_Id(Long agr_id);

    @Query("SELECT c FROM Cell c WHERE " +
            "(:id is NULL OR c.id = :id) " +
            "AND (:status IS null OR :status = '' OR c.status = :status) " +
            "AND (:sequenceNumber is NULL OR c.sequence_number = :sequenceNumber) " +
            "AND (:user_id IS null OR c.user.id = :user_id) " +
            "AND (:agr_id IS null OR c.agr.id = :agr_id)"
    )
    List<Cell> findWithFilter(Long id, String status, Integer sequenceNumber, Long user_id, Long agr_id);

    @Query("SELECT MAX(c.sequence_number) FROM Cell c WHERE c.agr.id = :agrId AND c.status LIKE 'active'")
    Integer findSequenceMax(@Param("agrId") Long agrId);
}
