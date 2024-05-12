package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionStatus;

@Data
@AllArgsConstructor
public class ExecutionResult {
    Long userId;
    String user;
    String message;
    ExecutionStatus status;
}
