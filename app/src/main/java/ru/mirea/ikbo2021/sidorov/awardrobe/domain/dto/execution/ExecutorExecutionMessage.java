package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutorExecutionMessage {
    private Long agrId;
    private Long userId;
    private Long executorId;
    private ExecutionStatus status;
    private Long cellId;
    private String message;
}
