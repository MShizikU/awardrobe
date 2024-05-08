package ru.mirea.ikbo2021.sidorov.awardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;

import java.util.List;

@Repository
public interface BranchRepository  extends JpaRepository<Branch, Long> {
    public List<Branch> findBranchesByCompanyId(Long companyId);
}
