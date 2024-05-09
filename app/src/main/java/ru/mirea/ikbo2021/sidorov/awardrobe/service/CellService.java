package ru.mirea.ikbo2021.sidorov.awardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Branch;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;
import ru.mirea.ikbo2021.sidorov.awardrobe.repository.CellRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CellService {
    private final CellRepository repository;
    private final UserService userService;
    private final AgrService agrService;

    /**
     * Получение списка ячеек по ID нардеробного ряда
     *
     * @return список ячеек
     */
    public List<Cell> getCellsByAgrId(Long agrId){
        return repository.findCellsByAgr_Id(agrId);
    }

}
