package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellMultipleCreateRequest;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.User;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.exception.general.EntityNotFound;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.CellRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CellService {
    private final CellRepository repository;
    private final UserService userService;
    private final AgrService agrService;
    private final VisitService visitService;

    /**
     * Сохранение ячейки
     */
    public Cell save(Cell cell){
        return repository.save(cell);
    }

    /**
     * Создание ячейки
     *
     * @param cell данные из запроса
     * @return ячейка
     */
    public Cell create(Cell cell){

        var agr = agrService.getByIdStrict(cell.getAgr().getId());

        var sequenceNumber = repository.findSequenceMax(agr.getId());

        return save(
                Cell.builder()
                        .status(cell.getStatus())
                        .sequence_number((sequenceNumber != null ? sequenceNumber + 1 : 1))
                        .agr(agr)
                        .build()
        );
    }

    /**
     * Создание множества ячеек
     *
     * @return список ячеек
     */
    public List<Cell> createMultiple(CellMultipleCreateRequest request){
        var agr = agrService.getByIdStrict(request.agr_id());

        List<Cell> cells = new LinkedList<>();

        var sequenceNumber = repository.findSequenceMax(agr.getId());
        sequenceNumber = sequenceNumber != null ? sequenceNumber + 1 : 1;

        for( int i = 0; i < request.amount(); i++){
            cells.add(save(
                    Cell.builder()
                            .status(Status.ACTIVE.getStatus())
                            .sequence_number(sequenceNumber + i)
                            .user(null)
                            .agr(agr)
                            .build()
            ));
        }

        return cells;
    }

    /**
     * Получение ячейки по ID
     *
     * @return ячейка
     */
    public Cell getByIdStrict(Long cellId){
        var cell = repository.findById(cellId);
        if (cell.isEmpty()) throw new EntityNotFound("cell","id", cellId.toString());
        return cell.get();
    }

    public List<Cell> getUserCell(){
        User user = userService.getCurrentUser();
        var cells = repository.findWithFilter(null, Status.INUSE.getStatus(),null, user.getId(), null);
        return cells;
    }

    public Cell getUserCurCell(Long userId){
        var cell = repository.findWithFilter(null, Status.INUSE.getStatus(),null, userId, null);
        return cell.isEmpty() ? null : cell.getFirst();
    }

    /**
     * Получение списка ячеек по ID нардеробного ряда
     *
     * @return список ячеек
     */
    public List<Cell> getCellsByAgrId(Long agrId){
        return repository.findCellsByAgr_Id(agrId);
    }

    /**
     * Получение фильтрованного списка ячеек
     *
     * @return список ячеек
     */
    public List<Cell> getByFilter(CellFilter filter){
        return repository.findWithFilter(
                filter.id(),
                filter.status(),
                filter.sequence_number(),
                filter.user_id(),
                filter.agr_id()
        );
    }

    /**
     * Получение списка ячеек
     *
     * @return список ячеек
     */
    public List<Cell> getAll(){
        return repository.findAll();
    }

    /**
     * Обновление данных ячейки
     *
     * @return обновленная ячейка
     */
    @Transactional
    public Cell update(Long cellId, Cell request){
        Cell cell = getByIdStrict(cellId);

        var agr = agrService.getByIdStrict(request.getAgr().getId());

        cell.setStatus(request.getStatus());
        cell.setAgr(agr);
        return save(cell);
    }

    /**
     * Установка использования ячейкой пользователя
     * @param user_id - ID пользователя
     * @param agr_id - ID гардеробного ряда
     * @return обновленная ячейка
     */
    @Transactional
    public Cell setUser(Long user_id, Long agr_id){
        var user = userService.getByIdStrict(user_id);
        var agr = agrService.getByIdStrict(agr_id);

        List<Cell> freeCells = repository.findWithFilter(null, Status.ACTIVE.getStatus(),null, user_id, agr_id);
        if (freeCells.isEmpty()) throw new EntityNotFound("cell", "status", "active");

        Cell freeCell = freeCells.getFirst();
        visitService.createVisit(user_id, freeCell);
        freeCell.setUser(user);
        freeCell.setStatus(Status.INUSE.getStatus());
        return save(freeCell);
    }

    /**
     * Установка использования ячейкой пользователя
     * @param user_id - ID пользователя
     * @param agr_id - ID гардеробного ряда
     * @return обновленная ячейка
     */
    @Transactional
    public Cell setUserInCell(Long user_id, Long agr_id, Long cellId){
        var user = userService.getByIdStrict(user_id);
        var agr = agrService.getByIdStrict(agr_id);
        var cell = getByIdStrict(cellId);
        if (cell.getStatus() != Status.INUSE.getStatus()){
            visitService.createVisit(user_id, cell);
            cell.setUser(user);
            cell.setStatus(Status.INUSE.getStatus());
            return save(cell);
        }
        return null;
    }

    /**
     * Снятие пользователя с ячейки
     * @param user_id - ID пользователя
     * @return обновленная ячейка
     */
    @Transactional
    public Cell removeUser(Long user_id){
        var user = userService.getByIdStrict(user_id);
        List<Cell> usedCells = repository.findWithFilter(null, Status.INUSE.getStatus(),null, user_id, null);
        if (usedCells.isEmpty()) throw new EntityNotFound("cell", "user_id", user_id.toString());
        Cell usedCell = usedCells.getFirst();
        visitService.closeVisit(user_id);
        usedCell.setStatus(Status.ACTIVE.getStatus());
        usedCell.setUser(null);
        return save(usedCell);
    }

    /**
     * Удаление ячейки
     *
     * @return удаление ячейки
     */
    public void deleteByIdSoft(Long cellId){
        Cell cell = getByIdStrict(cellId);
        cell.setStatus(Status.DELETED.getStatus());
        save(cell);
    }
}
