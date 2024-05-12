import React, {useContext} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";
import {Context} from "../../../index";
import UserChooseItem from "../../../components/Items/UserChooseItem/UserChooseItem";
import {useParams} from "react-router-dom";

const ChooseCompanyPage = () => {
    const params = useParams();
    const {store} = useContext(Context);
    return (
        <PageThemplate
            label="Выберите филиал">
            <ItemList
                fetchItems={(filter) => store.branches.getBranchByFilter(filter.id, "active", filter.name , null, params.id)}
                createItem={null}

                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <UserChooseItem key={key} item={item} baseUrl={"branches"} text={item.name}/>
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}

                filterFields={
                    [
                        { name: 'id', placeholder: 'ID', label: 'ID' },
                        { name: 'name', placeholder: 'Название', label: 'названию' },
                    ]
                }
            />
        </PageThemplate>
    );
};

export default ChooseCompanyPage;