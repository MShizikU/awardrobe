import React, {useContext} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import CompanyItem from "../../../components/Items/CompanyItem/CompanyItem";
import ListFilter from "../../../components/ListFilter/ListFilter";
import ItemList from "../../../enities/ItemList/ItemList";
import {Context} from "../../../index";
import UserChooseItem from "../../../components/Items/UserChooseItem/UserChooseItem";

const ChooseCompanyPage = () => {
    const {store} = useContext(Context);
    return (
        <PageThemplate
            label="Выберите компанию">
            <ItemList
                fetchItems={(filter) => store.companies.getCompaniesByFilter(filter.id, "active", filter.name ,filter.inn, null, null, null)}
                createItem={null}

                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <UserChooseItem key={key} item={item} baseUrl={"companies"} text={item.name}/>
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}

                filterFields={
                    [
                        { name: 'id', placeholder: 'ID', label: 'ID' },
                        { name: 'name', placeholder: 'Название', label: 'названию' },
                        { name: 'inn', placeholder: 'ИНН', label: 'ИНН' },
                    ]
                }
            />
        </PageThemplate>
    );
};

export default ChooseCompanyPage;