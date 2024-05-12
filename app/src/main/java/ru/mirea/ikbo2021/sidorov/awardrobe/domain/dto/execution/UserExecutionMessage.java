package ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionStatus;

@Data
@AllArgsConstructor
public class UserExecutionMessage {
    private Long agrId;
    private Long userId;
    private Long cellId;
    private String user;
    private ExecutionStatus status;
}
