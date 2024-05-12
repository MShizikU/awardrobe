import React, {useContext} from 'react';
import PageThemplate from "../../../components/PageThemplate/PageThemplate";
import {Context} from "../../../index";
import CompanyItem from "../../../components/Items/CompanyItem/CompanyItem";
import {observer} from "mobx-react-lite";
import ItemList from "../../../enities/ItemList/ItemList";
import ListFilter from "../../../components/ListFilter/ListFilter";

const CompaniesPage = () => {
    const {store} = useContext(Context);
    return (
        <PageThemplate
        label="Компании">
            <ItemList
                fetchItems={(filter) => store.companies.getCompaniesByFilter(filter.id, filter.status, null ,null, null, null, null)}
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
                            placeholder: '12345',
                            label: 'ИНН'
                        },
                        {
                            type: 'text',
                            placeholder: 'г. Кемерово, д.3',
                            label: 'Физический адрес'
                        },
                        {
                            type: 'text',
                            placeholder: 'г. Кемерово, д.3',
                            label: 'Юридический адрес'
                        },
                        {
                            type: 'text',
                            placeholder: '1',
                            label: 'ID менеджера'
                        }
                    ],
                    createItem: (status, name, inn, p_address, l_address, manager_id) => store.companies.createCompany( status, name, inn, p_address, l_address, manager_id)
                }}

                renderItem={({ key, item, wasChanged, setWasChanged }) => (
                    <CompanyItem key={key} company={item} wasChanged={wasChanged} setWasChanged={setWasChanged} />
                )}

                ListFilter={({ filter, setFilter }) => (
                    <ListFilter filter={filter} setFilter={setFilter} />
                )}
            />
        </PageThemplate>
    );
};

export default observer(CompaniesPage);