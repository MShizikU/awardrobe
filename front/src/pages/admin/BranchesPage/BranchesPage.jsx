import React, {useContext, useEffect, useState} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import {Context} from "../../../index";
import BranchItem from "../../../components/Items/BranchItem/BranchItem";
import ItemList from "../../../enities/ItemList/ItemList";
import ListFilter from "../../../components/ListFilter/ListFilter";

const BranchesPage = () => {

    const {store} = useContext(Context);

    return (
        <PageThemplate
            label="Филиалы">

            <ItemList
                fetchItems={(filter) => store.branches.getBranchByFilter(filter.id, filter.status, filter.name, null, null, null)}
                createItem={{
                    fields: [
                        {
                            type: 'text',
                            placeholder: 'ACTIVE',
                            label: 'Статус'
                        },
                        {
                            type: 'text',
                            placeholder: 'ООО Рога и Копыта',
                            label: 'Название'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID менеджера'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID компании'
                        }
                    ],
                    createItem: (status, name, manager_id, company_id) => store.branches.createBranch( status, name, manager_id, company_id )
                }}
                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <BranchItem key={key} branch={item} wasChanged={wasChanged} setWasChanged={setWasChanged} />
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}

                filterFields={
                    [
                        { name: 'id', placeholder: 'ID', label: 'ID' },
                        { name: 'status', placeholder: 'Статус', label: 'статусу' },
                        { name: 'name', placeholder: 'Название', label: 'названию' }
                    ]
                }
                />
        </PageThemplate>
    );
};

export default BranchesPage;