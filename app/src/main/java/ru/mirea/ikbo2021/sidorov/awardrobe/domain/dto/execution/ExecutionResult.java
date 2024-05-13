package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionActionShow;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionResult {
    Long userId;
    String message;
    ExecutionStatus status;
    ExecutionActionShow result;
}
