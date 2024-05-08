package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.ikbo2021.sidorov.awardrobe.mapper.AgrMapper;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.BranchService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.CellService;

@RestController
@RequiredArgsConstructor
public class AgrController {
    private final BranchService service;
    private final CellService cellService;
    private final AgrMapper mapper;


}
