import React, {useContext} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import {Context} from "../../../index";
import ItemList from "../../../enities/ItemList/ItemList";
import CellItem from "../../../components/Items/CellItem/CellItem";
import ListFilter from "../../../components/ListFilter/ListFilter";
import VisitItem from "../../../components/Items/VisitItem/VisitItem";

const VisitPage = () => {
    const {store} = useContext(Context);
    return (
        <PageThemplate label={"Посещения"}>
            <ItemList
                fetchItems={(filter) => store.visits.getVisitByFilter(filter.id, filter.user_id, filter.cell_id)}
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
                        { name: 'user_id', placeholder: 'ID пользователя', label: 'ID пользователя' },
                        { name: 'cell_id', placeholder: 'ID ячейки', label: 'ID ячейки' },
                    ]
                }
            />
        </PageThemplate>
    );
};

export default VisitPage;