package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.BranchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository repository;

    /**
     * Получение списка филиалов по ID компании
     *
     * @return список филиалов
     */
    public List<Branch> getBranchesByCompany(Long companyId){
        return repository.findBranchesByCompanyId(companyId);
    }
}
