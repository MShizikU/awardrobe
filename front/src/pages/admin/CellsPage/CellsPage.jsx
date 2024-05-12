import React, {useContext} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import BranchItem from "../../../components/Items/BranchItem/BranchItem";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";
import {Context} from "../../../index";
import CellItem from "../../../components/Items/CellItem/CellItem";

const CellsPage = () => {
    const {store} = useContext(Context);
    return (
        <PageThemplate label={"Ячейки"}>
            <ItemList
                fetchItems={(filter) => store.cells.getCellsByFilter(filter.id, filter.status, null, null, null, null)}
                createItem={{
                    fields: [
                        {
                            type: 'text',
                            placeholder: 'active',
                            label: 'Статус'
                        },
                        {
                            type: 'text',
                            placeholder: '2',
                            label: 'ID ряда'
                        }
                    ],
                    createItem: (status, agr_id) => store.cells.createCell( status, agr_id)
                }}
                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <CellItem key={key} cell={item} wasChanged={wasChanged} setWasChanged={setWasChanged} />
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

export default CellsPage;