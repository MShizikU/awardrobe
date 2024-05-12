import React, {useContext, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Context} from "../../../index";
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import Button from "../../../shared/UI/Button/Button";
import cls from './UserPage.module.css';
import {observer} from "mobx-react-lite";
import VisitItem from "../../../components/Items/VisitItem/VisitItem";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";

const UserPage = () => {
    const {store} = useContext(Context);
    const navigate = useNavigate();
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
                    <Button
                        className={"main"}
                        action={
                            () => {
                                store.logout();
                                navigate("/login");
                            }
                        }>Выйти</Button>
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
        </PageThemplate>
    );
};

export default observer(UserPage);