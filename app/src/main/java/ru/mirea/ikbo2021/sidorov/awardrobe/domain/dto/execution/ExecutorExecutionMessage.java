package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionStatus;

@Data
@AllArgsConstructor
public class ExecutorExecutionMessage {
    private Long agrId;
    private Long userId;
    private Long executorId;
    private String user;
    private String executor;
    private ExecutionStatus status;
    private Long cellId;
    private String message;
}
