import React, {useContext, useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Context} from "../../../index";
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import Button from "../../../shared/UI/Button/Button";
import cls from './UserPage.module.css';
import {observer} from "mobx-react-lite";
import VisitItem from "../../../components/Items/VisitItem/VisitItem";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import Form from "../../../enities/Form/Form";
import Modal from "../../../shared/UI/Modal/Modal";
import {message} from "antd";

var stompClient =null;

const UserPage = () => {
    const {store} = useContext(Context);
    const navigate = useNavigate();
    const [isEditVisible, setIsEditVisible] = useState(false);


    const onError = (err) => {
        console.log(err);

    }


    return (
        <PageThemplate
        label={"Пользователь"}>
            <div className={cls.horizontal_block}>
                <div className={cls.user_info}>
                    <div className={cls.info_row}>
                        <div className={cls.info_title}>
                            Имя пользователя
                        </div>
                        <div className={cls.info_value}>
                            {store.user.username}
                        </div>
                    </div>
                    <div className={cls.info_row}>
                        <div className={cls.info_title}>
                            Электронная почта
                        </div>
                        <div className={cls.info_value}>
                            {store.user.email}
                        </div>
                    </div>
                    <div className={cls.info_row}>
                        <div className={cls.info_title}>
                            Роль
                        </div>
                        <div className={cls.info_value}>
                            {store.user.role.name}
                        </div>
                    </div>
                    <div className={cls.info_row}>
                        <div className={cls.info_title}>
                            Пароль
                        </div>
                        <div className={cls.info_value}>
                            ********
                        </div>
                    </div>
                    <div className={cls.btn_row}>
                        <Button
                            className={"main"}
                            action={
                                () => {
                                    store.logout();
                                    navigate("/login");
                                }
                            }>Выйти</Button>
                        <Button
                            className={"secondary"}
                            action={
                                () => {
                                    setIsEditVisible(true);
                                }
                            }>Изменить</Button>
                    </div>

                </div>
                <div className={cls.visits}>
                    <div className={cls.visits_label}>
                        Ваши посещения
                    </div>
                    <ItemList
                        fetchItems={(filter) => store.visits.getVisitByFilter(filter.id, store.user.id, filter.cell_id)}
                        createItem={null}
                        renderItem={({ key, item, wasChanged, setWasChanged }) => (
                            <VisitItem key={key} visit={item} wasChanged={wasChanged} setWasChanged={setWasChanged} />
                        )}

                        ListFilter={({ filter, setFilter }) => (
                            <ListFilter filter={filter} setFilter={setFilter} />
                        )}

                        filterFields={
                            [
                                { name: 'id', placeholder: 'ID', label: 'ID' },
                                { name: 'cell_id', placeholder: 'ID ячейки', label: 'ID ячейки' },
                            ]
                        }
                    />
                </div>

            </div>
            <Modal
                visible={isEditVisible}
                setVisible={setIsEditVisible}
                >
            <Form
                label={"Сменить почту"}
                fields={[
                    {
                        type: 'text',
                        placeholder: 'active',
                        label: 'Статус',
                        preset: store.user.email
                    }
                ]}
                basicAction={async (email) => {
                    await store.users.changeEmail(store.user.id, email);
                    setIsEditVisible(false);
                    message("Перезагрузите страницу что бы увидеть изменения");
                }}
                secondaryAction={(username, password) => {
                    setIsEditVisible(false)
                }}
                actionButtonText={"Сохранить"}
                secondaryButtonText={"Отменить"}
            ></Form>
        </Modal>
        </PageThemplate>
    );
};

export default observer(UserPage);