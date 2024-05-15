import React, {useContext, useState} from 'react';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import {Context} from "../../../index";
import {useNavigate, useParams} from "react-router-dom";
import Button from "../../../shared/UI/Button/Button";
import cls from "./PerformAutomativeWardrobe.module.css";
import {observer} from "mobx-react-lite";
import PageThemplate from "../../../components/PageThemplate/PageThemplate";

const PerformAutomativeWardrobe = () => {

    const {store} = useContext(Context);
    const [connected, setConnected] = useState(false);
    const params = useParams();
    const agrId = params.id;
    const navigate= useNavigate();
    store.execution.connect();

    return (
        <PageThemplate label={"Взаимодействие с гардеробом"}>
            <div className={cls.perform_page}>
                <div className={cls.cell_data}>
                    {store.execution.curMessage != null ? store.execution.curMessage.result === "success_final" ? "Отправьте запрос" : store.execution.curMessage.message : "Отправьте запрос" }
                </div>

                <div className={cls.current_action}>
                    {
                        store.isExecutor()
                            ?
                            <div>
                                {!connected
                                    ?
                                    <Button className={"main"} action={() => {
                                        store.execution.sendExecutorMessage(agrId, null, store.user.id, "connect", null, null);
                                        setConnected(true);
                                    }}>
                                        Подключиться
                                    </Button>
                                    : store.execution.curMessage != null && store.execution.curMessage.result === "waiting"
                                        ?
                                        <Button className={"main"}
                                                action={() => {store.execution.sendExecutorMessage(agrId, null, null, "delete", null, null); navigate("/");}}>
                                            Отключиться
                                        </Button>
                                        : store.execution.curMessage != null && store.execution.curMessage.result === "transfer"
                                            ?
                                            <Button className={"main"}
                                                    action={() => store.execution.sendExecutorMessage(agrId, store.execution.curMessage.userId, store.user.id, "accept", store.execution.curMessage.message, null)}>
                                                Вещь отдана
                                            </Button>
                                            : store.execution.curMessage != null && store.execution.curMessage.result === "accept_transfer"
                                                ?
                                                <Button className={"main"}
                                                        action={() => store.execution.sendExecutorMessage(agrId, store.execution.curMessage.userId, store.user.id, "accept", store.execution.curMessage.message, null)}>
                                                    Подтвердить получение
                                                </Button>
                                                : store.execution.curMessage != null && store.execution.curMessage.result === "error" ?
                                                    <div className={cls.error_button}>
                                                        Что-то пошло не так
                                                    </div>
                                                    : <div></div>
                                }
                            </div>
                            :
                            <div>
                                {
                                    !connected
                                        ?
                                        <Button className={"main"} action={() => {
                                            store.execution.sendUserMessage(parseInt(agrId), store.user.id, null, "connect");
                                            setConnected(true);
                                        }}
                                        >
                                            Сдать вещь
                                        </Button>
                                        : store.execution.curMessage != null && store.execution.curMessage.result === "waiting"
                                            ? <div className={cls.result}>Ожидайте</div>
                                            : store.execution.curMessage != null && store.execution.curMessage.result === "transfer"
                                                ?
                                                <Button className={"main"} action={() => {
                                                    store.execution.sendUserMessage(parseInt(agrId), store.user.id, store.execution.curMessage.message, "send");
                                                    setConnected(true);
                                                }}
                                                >
                                                    Передать вещь
                                                </Button>
                                                : store.execution.curMessage != null && store.execution.curMessage.result === "success_more"
                                                ? <Button className={"main"} action={() => {
                                                    store.execution.sendUserMessage(parseInt(agrId), store.user.id, null, "connect");
                                                    setConnected(true);
                                                }}
                                                >
                                                    Хочу забрать
                                                </Button>
                                                : store.execution.curMessage != null && store.execution.curMessage.result === "success_final"
                                                ? <Button className={"main"} action={() => {
                                                            store.execution.sendUserMessage(parseInt(agrId), store.user.id, null, "connect");
                                                            setConnected(true);
                                                }}
                                                >
                                                    Сдать вещь
                                                </Button>
                                                : store.execution.curMessage != null && store.execution.curMessage.result === "error" ?
                                                <div className={cls.error_button}>
                                                    Что-то пошло не так
                                                </div>
                                                : <div></div>

                                }
                            </div>
                    }

                </div>
            </div>
        </PageThemplate>
    );
};

export default observer(PerformAutomativeWardrobe);