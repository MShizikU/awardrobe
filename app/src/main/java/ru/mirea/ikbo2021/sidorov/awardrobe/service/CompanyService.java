package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.company.CompanyFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;
    private final UserService userService;


    /**
     * Сохранение компани
     */
    public Company save(Company company) {
        return repository.save(company);
    }

    /**
     * Создание компании
     *
     * @param company данные из запроса
     * @return созданная компания
     */
    @Transactional
    public Company create(Company company) {
        var manager = userService.getByIdStrict(company.getManager().getId());

        return save(
                Company.builder()
                        .status(company.getStatus())
                        .inn(company.getInn())
                        .name(company.getName())
                        .physical_address(company.getPhysical_address())
                        .legal_address(company.getLegal_address())
                        .manager(manager)
                        .build()
        );
    }

    /**
     * Получение данных компании по id
     *
     * @return компания
     */
    public Company getById(Long id) {
        Optional<Company> company = repository.findById(id);
        if (company.isEmpty()) throw new EntityNotFound("company", "id", id.toString());
        return company.get();
    }

    /**
     * Получение списка компаний по статусу
     *
     * @return список компаний
     */
    public List<Company> getByStatus(String status){
        return repository.findCompaniesByStatus(status);
    }

    /**
     * Получение по фильтру
     *
     * @param filter фильтр
     * @return Найденные филиалы
     */
    public List<Company> findByFilter(CompanyFilter filter) {

        return repository.findAllWithFilter(
                filter.id(),
                filter.status(),
                filter.inn(),
                filter.physical_address(),
                filter.legal_address(),
                filter.manager_id()
        );
    }

    /**
     * Получение всех компаний
     *
     * @return список компаний
     */
    public List<Company> getAll() {
        return repository.findAll();
    }

    /**
     * Обновление компании
     *
     * @return обновленные данные
     */
    @Transactional
    public Company update(Long companyId, Company company) {
        Company toUpdate = getById(companyId);

        var manager = userService.getByIdStrict(company.getManager().getId());

        toUpdate.setStatus(company.getStatus());
        toUpdate.setInn(company.getInn());
        toUpdate.setPhysical_address(company.getPhysical_address());
        toUpdate.setLegal_address(company.getLegal_address());
        toUpdate.setManager(manager);
        toUpdate.setName(company.getName());

        return repository.save(toUpdate);

    }

    /**
     * Удаление компании
     *
     * @return void
     */
    public void deleteByIdSoft(Long companyId) {
        var company = getById(companyId);
        company.setStatus(Status.DELETED.getStatus());
        repository.save(company);
    }
}
