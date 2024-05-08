package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
