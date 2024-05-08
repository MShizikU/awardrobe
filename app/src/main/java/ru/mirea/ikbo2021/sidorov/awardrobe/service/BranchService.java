package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.branch.BranchFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityFieldNotValid;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.BranchRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository repository;
    private final CompanyService companyService;
    private final UserService userService;

    /**
     * Сохранение филиала
     */
    public Branch save(Branch branch){
        return repository.save(branch);
    }

    /**
     * Создание филиала
     *
     * @param branch данные из запроса
     * @return филиал
     */
    public Branch create(Branch branch){
        var manager = userService.getByIdStrict(branch.getManager().getId());

        var company = companyService.getById(branch.getCompany().getId());

        return save(
                Branch.builder()
                        .status(branch.getStatus())
                        .name(branch.getName())
                        .manager(manager)
                        .company(company)
                        .build()
        );
    }

    /**
     * Получение филиала по ID
     *
     * @return филиал
     */
    public Branch getById(Long id){
        var branch = repository.findById(id);
        if (branch.isEmpty()) throw new EntityNotFound("branch", "ID", id.toString());
        return branch.get();
    }

    /**
     * Получение списка филиалов по ID компании
     *
     * @return список филиалов
     */
    public List<Branch> getBranchesByCompany(Long companyId){
        return repository.findBranchesByCompany_Id(companyId);
    }

    /**
     * Получение по фильтру
     *
     * @param filter фильтр
     * @return Найденные филиал
     */
    public List<Branch> getByFilter(BranchFilter filter) {

        return repository.findAllWithFilter(
                filter.id(),
                filter.status(),
                filter.name(),
                filter.manager_id(),
                filter.company_id()
        );
    }

    /**
     * Получение списка филиалов
     *
     * @return список филиалов
     */
    public List<Branch> getAll(){
        return repository.findAll();
    }

    /**
     * Обновление данных филиала
     *
     * @return филиал
     */
    @Transactional
    public Branch update(Long branchId, Branch request){
        Branch branch = getById(branchId);

        var manager = userService.getByIdStrict(branch.getManager().getId());
        var company = companyService.getById(branch.getCompany().getId());

        try {
            Status.valueOf(request.getStatus());
            branch.setStatus(request.getStatus());
        }
        catch (IllegalArgumentException e){
            throw new EntityFieldNotValid("branch","status", company.getStatus());
        }

        branch.setStatus(request.getStatus());
        branch.setName(request.getName());
        branch.setManager(manager);
        branch.setCompany(company);
        return save(branch);
    }

    /**
     * Удаление филиала
     *
     * @return void
     */
    public void deleteByIdSoft(Long id){
        var branch = getById(id);
        branch.setStatus(Status.DELETED.getStatus());
        repository.save(branch);
    }
}
