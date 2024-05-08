package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.AgrRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgrService {
    private final AgrRepository repository;
    private final UserService userService;
    private final BranchService branchService;

    /**
     * Получение списка гардеробных рядов по ID филиала
     *
     * @return список гардеробных рядов (AGR)
     */
    public List<Agr> getAgrsByBranchId(Long id){
        return  repository.findAgrsByBranch_Id(id);
    }
}
