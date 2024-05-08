package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;

import java.util.List;

@Repository
public interface AgrRepository extends JpaRepository<Agr, Long> {
    public List<Agr> findAgrsByBranch_Id(Long id);
}
