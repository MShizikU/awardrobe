import React, {useContext, useEffect, useState} from 'react';
import {Context} from "../../../index";
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import cls from "./AgrsPage.module.css";
import AgrItem from "../../../components/Items/AgrItem/AgrItem";
import ItemList from "../../../enities/ItemList/ItemList";
import ListFilter from "../../../components/ListFilter/ListFilter";

const AgrsPage = () => {
    const {store} = useContext(Context);
    return (
        <PageThemplate
            label={"Ряды"}
            >
            <ItemList
                fetchItems={(filter) => store.agrs.getAgrByFilter(filter.id, filter.status, null, null, null, null)}
                createItem={{
                    fields: [
                        {
                            type: 'text',
                            placeholder: 'active',
                            label: 'Статус'
                        },
                        {
                            type: 'text',
                            placeholder: '09:00',
                            label: 'Время открытия'
                        },
                        {
                            type: 'text',
                            placeholder: '18:00',
                            label: 'Время закрытия'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID филиала'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID исполнителя'
                        }
                    ],
                    createItem: (status, open_time, close_time,branch_id, executor_id ) => store.agrs.createAgr(status, open_time, close_time, executor_id, branch_id)
                }}

                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <AgrItem key={key} agr={item} wasChanged={wasChanged} setWasChanged={setWasChanged} />
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}

                filterFields={
                    [
                        { name: 'id', placeholder: 'ID', label: 'ID' },
                        { name: 'status', placeholder: 'Статус', label: 'статусу' }
                    ]
                }
                />
        </PageThemplate>
    );
};

export default AgrsPage;