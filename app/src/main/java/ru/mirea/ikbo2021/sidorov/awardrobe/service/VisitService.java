package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.visit.VisitFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Visit;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.visit.VisitAlreadyExists;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.VisitRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository repository;
    private final UserService userService;

    /**
     * Сохранение данных о визите
     *
     * @param visit визит
     * @return сохраненый визит
     */
    public Visit save(Visit visit){
        return repository.save(visit);
    }

    /**
     * Создание визита
     * @param user_id ID посетителя
     * @param cell ID ячейка
     */
    public void createVisit(Long user_id, Cell cell){
        var user = userService.getByIdStrict(user_id);

        if (repository.findByUserIdAndEndTime(user_id, null).isPresent()) throw new VisitAlreadyExists(user_id, cell.getId());

        save(
                Visit.builder()
                        .start_time(new Timestamp(System.currentTimeMillis()))
                        .user(user)
                        .cell(cell)
                        .build()
        );
    }

    /**
     * Закрытие посещения
     * @param user_id пользователь
     */
    public void closeVisit(Long user_id){
        var user = userService.getByIdStrict(user_id);
        var visit = repository.findByUserIdAndEndTime(user_id, null);
        if (visit.isEmpty()) throw new EntityNotFound("visit", "user_id", user_id.toString());
        var toUpdate = visit.get();
        toUpdate.setUser(null);
        toUpdate.setEnd_time(new Timestamp(System.currentTimeMillis()));
        save(
            toUpdate
        );
    }

    /**
     * Получение визита по ID
     *
     * @return визит
     */
    public Visit getByIdStrict(Long visitId){
        var visit = repository.findById(visitId);
        if (visit.isEmpty()) throw new EntityNotFound("visit", "ID", visitId.toString());
        return visit.get();
    }

    /**
     * Получение фильтрованного списка визитов
     *
     * @return список визитов
     */
    public List<Visit> getByFilter(VisitFilter filter){
        return repository.findByFilter(
                filter.id(),
                filter.cell_id(),
                filter.user_id()
        );
    }

    /**
     * Получение списков всех визитов
     *
     * @return список визитов
     */
    public List<Visit> getAll(){
        return repository.findAll();
    }
}
