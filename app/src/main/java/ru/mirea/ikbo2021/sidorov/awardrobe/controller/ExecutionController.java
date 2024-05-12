package ru.mirea.ikbo2021.sidorov.awardrobe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.cell.CellFilter;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution.ExecutionResult;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution.ExecutorExecutionMessage;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.dto.execution.UserExecutionMessage;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.model.Cell;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionStatus;
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.Status;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.AgrService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.CellService;
import ru.mirea.ikbo2021.sidorov.awardrobe.service.UserService;

@Controller
public class ExecutionController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired private AgrService service;
    @Autowired private CellService cellService;

    @MessageMapping("/executor")
    public void processExecutor(@Payload ExecutorExecutionMessage chatMessage) {
        if (chatMessage.getStatus().equals(ExecutionStatus.CONNECT)){
            service.setAgrExecutor(chatMessage.getAgrId(), chatMessage.getExecutorId(), chatMessage.getExecutor());
        }
        else if (chatMessage.getStatus().equals(ExecutionStatus.SEND)){
            var agr = service.getByIdStrict(chatMessage.getAgrId());
            messagingTemplate.convertAndSendToUser(
                    agr.getExecutorWsId(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            chatMessage.getUser(),
                            chatMessage.getCellId().toString(),
                            ExecutionStatus.SEND
                    )
            );
        }
        else if (chatMessage.getStatus().equals(ExecutionStatus.ACCEPT)){
            var cell = cellService.getByIdStrict(chatMessage.getCellId());
            var agr = service.getByIdStrict(chatMessage.getAgrId());
            if (cell.getUser() == null){
                cellService.setUser(chatMessage.getUserId(), chatMessage.getCellId());
            }else{
                cellService.removeUser(chatMessage.getUserId());
            }
            messagingTemplate.convertAndSendToUser(
                    agr.getExecutorWsId(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            chatMessage.getUser(),
                            chatMessage.getCellId().toString(),
                            ExecutionStatus.SUCCESS
                    )
            );
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getUser(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            chatMessage.getUser(),
                            chatMessage.getCellId().toString(),
                            ExecutionStatus.SUCCESS
                    )
            );

        }
    }

    @MessageMapping("/user")
    public void proccessUser(@Payload UserExecutionMessage chatMessage) {
        if (chatMessage.getStatus().equals(ExecutionStatus.CONNECT)){
            var userCell = cellService.getUserCurCell(chatMessage.getUserId());
            if (userCell != null){
                var agr = service.getByIdStrict(chatMessage.getAgrId());
                messagingTemplate.convertAndSendToUser(
                        agr.getExecutorWsId(),"/queue/messages",
                        new ExecutionResult(
                                chatMessage.getUserId(),
                                chatMessage.getUser(),
                                chatMessage.getCellId().toString(),
                                ExecutionStatus.SEND
                        )
                );
            }
            else{
                var cells = cellService.getByFilter(new CellFilter(null, Status.ACTIVE.getStatus(), null, null, chatMessage.getAgrId()));
                if (cells.isEmpty()){
                    messagingTemplate.convertAndSendToUser(
                            chatMessage.getUser(),"/queue/messages",
                            new ExecutionResult(
                                    chatMessage.getUserId(),
                                    chatMessage.getUser(),
                                    "Нет свободных ячеек",
                                    ExecutionStatus.ERROR
                            )
                    );
                }
                else{
                    var cell = cells.getFirst();
                    messagingTemplate.convertAndSendToUser(
                            chatMessage.getUser(),"/queue/messages",
                            new ExecutionResult(
                                    chatMessage.getUserId(),
                                    chatMessage.getUser(),
                                    cell.getId().toString(),
                                    ExecutionStatus.SUCCESS
                            )
                    );
                    var agr = service.getByIdStrict(chatMessage.getAgrId());
                    messagingTemplate.convertAndSendToUser(
                            agr.getExecutorWsId(),"/queue/messages",
                            new ExecutionResult(
                                    chatMessage.getUserId(),
                                    chatMessage.getUser(),
                                    chatMessage.getCellId().toString(),
                                    ExecutionStatus.CONNECT
                            )
                    );
                }
            }
        }
        else if (chatMessage.getStatus().equals(ExecutionStatus.SEND)){
            var agr = service.getByIdStrict(chatMessage.getAgrId());
            var cell = service.getByIdStrict(chatMessage.getCellId());
            messagingTemplate.convertAndSendToUser(
                    agr.getExecutorWsId(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            chatMessage.getUser(),
                            chatMessage.getCellId().toString(),
                            ExecutionStatus.SEND
                    )
            );
        }
    }
}
