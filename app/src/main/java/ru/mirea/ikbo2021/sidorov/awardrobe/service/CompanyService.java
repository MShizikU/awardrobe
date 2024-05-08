package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Company;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityFieldNotValid;
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
     * Создание комментария
     *
     * @param company данные из запроса
     * @return созданная компания
     */
    @Transactional
    public Company create(Company company) {
        var manager = userService.getById(company.getManager().getId());
        if (manager.isEmpty()){
            throw new EntityNotFound("Пользователь", "ID", company.getManager().getId().toString());
        }
        return save(
                Company.builder()
                        .status(company.getStatus())
                        .inn(company.getInn())
                        .physicalAddress(company.getPhysicalAddress())
                        .legalAddress(company.getLegalAddress())
                        .manager(manager.get())
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
        Optional<Company> companyOptional = repository.findById(companyId);
        if (companyOptional.isEmpty()) throw new EntityNotFound("company", "id", companyId.toString());

        var manager = userService.getById(company.getManager().getId());
        if (manager.isEmpty()){
            throw new EntityNotFound("Пользователь", "ID", company.getManager().getId().toString());
        }

        Company toUpdate = companyOptional.get();

        try {
            Status.valueOf(company.getStatus());
            toUpdate.setStatus(company.getStatus());
        }
        catch (IllegalArgumentException e){
            throw new EntityFieldNotValid("company","status", company.getStatus());
        }

        if (company.getInn().isEmpty()) throw new EntityFieldNotValid("company","inn", company.getInn());
        toUpdate.setInn(company.getInn());

        if (company.getPhysicalAddress().isEmpty()) throw new EntityFieldNotValid("company","physicalAddress", company.getPhysicalAddress());
        toUpdate.setPhysicalAddress(company.getPhysicalAddress());

        if (company.getLegalAddress().isEmpty()) throw new EntityFieldNotValid("company","legalAddress", company.getLegalAddress());
        toUpdate.setLegalAddress(company.getLegalAddress());

        toUpdate.setManager(manager.get());

        return repository.save(toUpdate);

    }

    /**
     * Удаление компании
     *
     * @return void
     */
    public void deleteById(Long companyId) {
        Optional<Company> companyOptional = repository.findById(companyId);
        if (companyOptional.isEmpty()) throw new EntityNotFound("company", "id", companyId.toString());
        Company company = companyOptional.get();
        company.setStatus(Status.DELETED.getStatus());
        repository.save(company);
    }
}
