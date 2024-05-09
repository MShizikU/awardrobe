package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;

import java.util.List;

@Repository
public interface CellRepository  extends JpaRepository<Cell, Long> {
    public List<Cell> findCellsByAgr_Id(Long agr_id);
}
