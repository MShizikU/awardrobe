import React, {useContext, useEffect} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";
import {Context} from "../../../index";
import UserChooseItem from "../../../components/Items/UserChooseItem/UserChooseItem";
import {useNavigate, useParams} from "react-router-dom";

const ChooseCompanyPage = () => {
    const params = useParams();
    const {store} = useContext(Context);
    const navigate = useNavigate();
    useEffect(() => {
        store.cells.getCurrentCell().then((response) => response != null ? navigate(`/agr/${response.agr_id}`) : "")
    }, [])
    return (
        <PageThemplate
            label="Выберите филиал">
            <ItemList
                fetchItems={(filter) => store.branches.getBranchByFilter(filter.id, store.isExecutor() ? "inactive" : "active", filter.name , null, params.id)}
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