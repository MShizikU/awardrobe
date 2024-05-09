package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.agr.AgrFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Agr;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.AgrRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgrService {
    private final AgrRepository repository;
    private final UserService userService;
    private final BranchService branchService;

    /**
     * Сохранение гардеробного ряда
     */
    public Agr save(Agr agr){
        return repository.save(agr);
    }

    /**
     * Создание гардеробного ряда
     *
     * @param agr данные из запроса
     * @return филиал
     */
    public Agr create(Agr agr){
        var executor = userService.getByIdStrict(agr.getExecutor().getId());

        var branch = branchService.getById(agr.getBranch().getId());

        return save(
                Agr.builder()
                        .status(agr.getStatus())
                        .openTime(agr.getOpenTime())
                        .closeTime(agr.getCloseTime())
                        .executor(executor)
                        .branch(branch)
                        .build()
        );
    }

    /**
     * Получние гардеробного ряда по ID
     *
     * @return гардеробный ряд
     */
    public Agr getByIdStrict(Long agrId){
        var agr = repository.findById(agrId);
        if (agr.isEmpty()) throw new EntityNotFound("agr", "id" , agrId.toString());
        return agr.get();
    }

    /**
     * Получение списка гардеробных рядов по ID филиала
     *
     * @return список гардеробных рядов (AGR)
     */
    public List<Agr> getAgrsByBranchId(Long id){
        return  repository.findAgrsByBranch_Id(id);
    }

    /**
     * Получение фильтрованного списка гардеробных рядов
     *
     * @return список гардеробных рядов
     */
    public List<Agr> getByFilter(AgrFilter filter){
        return repository.findAllWithFilter(
                filter.id(),
                filter.status(),
                filter.open_time(),
                filter.close_time(),
                filter.executor_id(),
                filter.branch_id()
        );
    }

    /**
     * Получение списка гардеробных рядов
     *
     * @return список гардеробных рядов
     */
    public List<Agr> getAll(){
        return repository.findAll();
    }

    /**
     * Обновление гардеробного ряда
     *
     * @return обновленный гардеробный ряд
     */
    @Transactional
    public Agr update(Long agrId, Agr request){
        Agr agr = getByIdStrict(agrId);

        if (request.getExecutor() != null){
            var executor = userService.getByIdStrict(request.getExecutor().getId());
            agr.setExecutor(executor);
        }

        var branch = branchService.getById(request.getBranch().getId());

        agr.setStatus(request.getStatus());
        agr.setOpenTime(request.getOpenTime());
        agr.setCloseTime(request.getCloseTime());
        agr.setBranch(branch);

        return save(agr);
    }

    /**
     * Удаление гардеробного ряда
     *
     * @return void
     */
    public void deleteByIdSoft(Long agrId){
        Agr agr = getByIdStrict(agrId);
        agr.setStatus(Status.DELETED.getStatus());
        save(agr);
    }
}
