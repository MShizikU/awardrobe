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
import ru.mirea.ikbo2021.sidorov.awardrobe.domain.utils.ExecutionActionShow;
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
        var agr = service.getByIdStrict(chatMessage.getAgrId());
        try {
            if (chatMessage.getStatus().equals(ExecutionStatus.CONNECT)){
                //service.removeAgrExecutor(chatMessage.getAgrId());
                service.setAgrExecutor(chatMessage.getAgrId(), chatMessage.getExecutorId());
                messagingTemplate.convertAndSendToUser(
                        chatMessage.getExecutorId().toString(),"/queue/messages",
                        new ExecutionResult(
                                chatMessage.getUserId(),
                                "",
                                ExecutionStatus.SEND,
                                ExecutionActionShow.WAITING
                        )
                );
            }
            else if (chatMessage.getStatus().equals(ExecutionStatus.ACCEPT)){
                var cell = cellService.getByIdStrict(chatMessage.getCellId());
                var result = false;
                if (cell.getUser() == null){
                    cellService.setUserInCell(chatMessage.getUserId(), chatMessage.getAgrId(), chatMessage.getCellId() );
                    messagingTemplate.convertAndSendToUser(
                            chatMessage.getUserId().toString(),"/queue/messages",
                            new ExecutionResult(
                                    chatMessage.getUserId(),
                                    chatMessage.getCellId().toString(),
                                    ExecutionStatus.SUCCESS,
                                    ExecutionActionShow.SUCCESS_MORE
                            )
                    );
                }
                else{
                    cellService.removeUser(chatMessage.getUserId());
                    messagingTemplate.convertAndSendToUser(
                            chatMessage.getUserId().toString(),"/queue/messages",
                            new ExecutionResult(
                                    chatMessage.getUserId(),
                                    chatMessage.getCellId().toString(),
                                    ExecutionStatus.SUCCESS,
                                    ExecutionActionShow.SUCCESS_FINAL
                            )
                    );
                }
                messagingTemplate.convertAndSendToUser(
                        agr.getExecutor().getId().toString(),"/queue/messages",
                        new ExecutionResult(
                                chatMessage.getUserId(),
                                chatMessage.getCellId().toString(),
                                ExecutionStatus.SUCCESS,
                                ExecutionActionShow.WAITING
                        )
                );

            }
            else if (chatMessage.getStatus().equals(ExecutionStatus.DELETE)){
                service.removeAgrExecutor(chatMessage.getAgrId());
            }
        }
        catch (Exception e){
            messagingTemplate.convertAndSendToUser(
                    agr.getExecutor().getId().toString(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            "Произошла не предвиденная ошибка",
                            ExecutionStatus.SEND,
                            ExecutionActionShow.ERROR
                    )
            );
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getUserId().toString(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            "Произошла не предвиденная ошибка",
                            ExecutionStatus.CONNECT,
                            ExecutionActionShow.ERROR
                    )
            );
        }

    }

    @MessageMapping("/user")
    public void proccessUser(@Payload UserExecutionMessage chatMessage) {
        var agr = service.getByIdStrict(chatMessage.getAgrId());
        try{
            if (chatMessage.getStatus().equals(ExecutionStatus.CONNECT)){
                var userCell = cellService.getUserCurCell(chatMessage.getUserId());
                if (userCell != null){
                    messagingTemplate.convertAndSendToUser(
                            agr.getExecutor().getId().toString(),"/queue/messages",
                            new ExecutionResult(
                                    chatMessage.getUserId(),
                                    userCell.getId().toString(),
                                    ExecutionStatus.SEND,
                                    ExecutionActionShow.TRANSFER
                            )
                    );
                    messagingTemplate.convertAndSendToUser(
                            chatMessage.getUserId().toString(),"/queue/messages",
                            new ExecutionResult(
                                    chatMessage.getUserId(),
                                    userCell.getId().toString(),
                                    ExecutionStatus.SEND,
                                    ExecutionActionShow.WAITING
                            )
                    );
                }
                else{
                    var cells = cellService.getByFilter(new CellFilter(null, Status.ACTIVE.getStatus(), null, null, chatMessage.getAgrId()));
                    if (cells.isEmpty()){
                        messagingTemplate.convertAndSendToUser(
                                chatMessage.getUserId().toString(),"/queue/messages",
                                new ExecutionResult(
                                        chatMessage.getUserId(),
                                        "Нет свободных ячеек",
                                        ExecutionStatus.ERROR,
                                        ExecutionActionShow.ERROR
                                )
                        );
                    }
                    else{
                        var cell = cells.getFirst();
                        messagingTemplate.convertAndSendToUser(
                                chatMessage.getUserId().toString(),"/queue/messages",
                                new ExecutionResult(
                                        chatMessage.getUserId(),
                                        cell.getId().toString(),
                                        ExecutionStatus.SEND,
                                        ExecutionActionShow.TRANSFER
                                )
                        );
                        messagingTemplate.convertAndSendToUser(
                                agr.getExecutor().getId().toString(),"/queue/messages",
                                new ExecutionResult(
                                        chatMessage.getUserId(),
                                        cell.getId().toString(),
                                        ExecutionStatus.CONNECT,
                                        ExecutionActionShow.WAITING
                                )
                        );
                    }
                }
            }
            else if (chatMessage.getStatus().equals(ExecutionStatus.SEND)){

                var cell = cellService.getByIdStrict(chatMessage.getCellId());
                messagingTemplate.convertAndSendToUser(
                        agr.getExecutor().getId().toString(),"/queue/messages",
                        new ExecutionResult(
                                chatMessage.getUserId(),
                                chatMessage.getCellId().toString(),
                                ExecutionStatus.SEND,
                                ExecutionActionShow.ACCEPT_TRANSFER
                        )
                );
                messagingTemplate.convertAndSendToUser(
                        chatMessage.getUserId().toString(),"/queue/messages",
                        new ExecutionResult(
                                chatMessage.getUserId(),
                                cell.getId().toString(),
                                ExecutionStatus.CONNECT,
                                ExecutionActionShow.WAITING
                        )
                );
            }
        }catch (Exception e){
            messagingTemplate.convertAndSendToUser(
                    agr.getExecutor().getId().toString(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            "Произошла не предвиденная ошибка",
                            ExecutionStatus.SEND,
                            ExecutionActionShow.ERROR
                    )
            );
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getUserId().toString(),"/queue/messages",
                    new ExecutionResult(
                            chatMessage.getUserId(),
                            "Произошла не предвиденная ошибка",
                            ExecutionStatus.CONNECT,
                            ExecutionActionShow.ERROR
                    )
            );
        }

    }
}
